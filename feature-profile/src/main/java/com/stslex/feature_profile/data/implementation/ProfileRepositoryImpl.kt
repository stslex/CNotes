package com.stslex.feature_profile.data.implementation

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_data_source.service.abstraction.LocalNotesService
import com.stslex.core_firebase.abstraction.FirebaseAppInitialisationUtil
import com.stslex.core_model.mapper.MapperNoteListRemote
import com.stslex.core_model.mapper.MapperNoteSize
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.core_remote_data_source.service.abstraction.FirebaseNotesService
import com.stslex.feature_profile.data.abstraction.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(
    private val remoteNotesService: FirebaseNotesService,
    private val localNotesService: LocalNotesService,
    private val firebaseInitializer: FirebaseAppInitialisationUtil,
    private val mapperNoteSize: MapperNoteSize,
    private val transformerNoteEquals: TransformerEqualTypeValues<List<NoteEntity>, Int>,
    private val mapperNoteListRemote: MapperNoteListRemote,
) : ProfileRepository {

    override fun signOut() {
        Firebase.auth.signOut()
    }

    override fun initFirebaseApp() {
        firebaseInitializer.invoke()
    }

    override suspend fun downloadNotes(): ValueState<Unit> =
        when (val localNotesState = localNotesService.getLocalNotesRow()) {
            is ValueState.Success -> when (val remoteNotesState =
                remoteNotesService.getRemoteNotes()) {
                is ValueState.Success -> {
                    val notesToWrite = remoteNotesState.data.filterNot {
                        localNotesState.data.contains(it)
                    }
                    localNotesService.uploadNotesToLocalDatabase(notesToWrite)
                }
                is ValueState.Failure -> ValueState.Failure(remoteNotesState.exception)
                is ValueState.Loading -> ValueState.Loading
            }
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }

    override suspend fun uploadNotes(): Flow<ValueState<Void>> = flow {
        val result = when (val localNotesState = localNotesService.getLocalNotesRow()) {
            is ValueState.Success -> remoteNotesService.uploadNotesToRemoteDatabase(
                mapperNoteListRemote.map(localNotesState.data)
            )
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }
        emit(result)
    }

    override val remoteNotesSize: Flow<ValueState<Int>>
        get() = remoteNotesService.remoteNotes.map {
            it.map(mapperNoteSize)
        }

    override val syncNotesSize: Flow<ValueState<Int>>
        get() = localNotesService.localNotes.combineTransform(remoteNotesService.remoteNotes) { local, remote ->
            val result = local.transform(transformerNoteEquals, remote)
            emit(result)
        }

    override val localNotes: Flow<ValueState<List<NoteEntity>>>
        get() = localNotesService.localNotes

    override val localNotesSize: Flow<ValueState<Int>>
        get() = localNotesService.notesSize
}