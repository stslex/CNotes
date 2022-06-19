package com.stslex.feature_profile.data.implementation

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_data_source.service.abstraction.LocalNotesService
import com.stslex.core_firebase.abstraction.FirebaseAppInitialisationUtil
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_remote_data_source.service.abstraction.FirebaseNotesService
import com.stslex.feature_profile.data.abstraction.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val remoteNotesService: FirebaseNotesService,
    private val localNotesService: LocalNotesService,
    private val firebaseInitializer: FirebaseAppInitialisationUtil
) : ProfileRepository {

    override fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun uploadNotesToRemoteDatabase(
        updateMap: Map<String, Any>
    ): ValueState<Void> = remoteNotesService.uploadNotesToRemoteDatabase(updateMap)

    override suspend fun uploadNotesToLocalDatabase(
        notes: List<NoteEntity>
    ): ValueState<Unit> = localNotesService.uploadNotesToLocalDatabase(notes)

    override suspend fun getRealtimeRemoteNotes(): Flow<ValueState<List<NoteEntity>>> =
        remoteNotesService.getRealtimeRemoteNotes()

    override suspend fun getRemoteNotes(): ValueState<List<NoteEntity>> =
        remoteNotesService.getRemoteNotes()

    override suspend fun getLocalNotes(): ValueState<List<NoteEntity>> =
        localNotesService.getLocalNotesRow()

    override val localNotesSize: Flow<ValueState<Int>>
        get() = localNotesService.notesSize

    override fun initFirebaseApp() {
        firebaseInitializer.invoke()
    }
}