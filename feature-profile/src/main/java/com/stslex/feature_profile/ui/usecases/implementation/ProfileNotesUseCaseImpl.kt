package com.stslex.feature_profile.ui.usecases.implementation

import com.stslex.core.ValueState
import com.stslex.core_model.mapper.MapperNoteListRemote
import com.stslex.core_model.mapper.MapperNoteSize
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.feature_profile.data.abstraction.ProfileRepository
import com.stslex.feature_profile.ui.usecases.abstraction.ProfileNotesUseCase
import kotlinx.coroutines.flow.*

class ProfileNotesUseCaseImpl(
    private val repository: ProfileRepository,
    private val mapperNoteSize: MapperNoteSize,
    private val transformerNoteEquals: TransformerEqualTypeValues<List<NoteEntity>, Int>,
    private val mapperNoteListRemote: MapperNoteListRemote
) : ProfileNotesUseCase {

    override suspend fun downloadNotes(): ValueState<Unit> =
        when (val localNotesState = repository.getLocalNotes()) {
            is ValueState.Success -> when (val remoteNotesState = repository.getRemoteNotes()) {
                is ValueState.Success -> {
                    val notesToWrite = remoteNotesState.data.filterNot {
                        localNotesState.data.contains(it)
                    }
                    repository.uploadNotesToLocalDatabase(notesToWrite)
                }
                is ValueState.Failure -> ValueState.Failure(remoteNotesState.exception)
                is ValueState.Loading -> ValueState.Loading
            }
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }

    override val localNotesSize: Flow<ValueState<Int>>
        get() = repository.localNotesSize

    override suspend fun remoteNotesSize(): Flow<ValueState<Int>> =
        repository.getRealtimeRemoteNotes().map {
            it.map(mapperNoteSize)
        }

    override suspend fun syncNotesSize(): Flow<ValueState<Int>> = flowOf(repository.getLocalNotes())
        .combineTransform(repository.getRealtimeRemoteNotes()) { local, remote ->
            val result = local.transform(transformerNoteEquals, remote)
            emit(result)
        }

    override suspend fun uploadNotes(): Flow<ValueState<Void>> = flow {
        val result = when (val localNotesState = repository.getLocalNotes()) {
            is ValueState.Success -> repository.uploadNotesToRemoteDatabase(
                mapperNoteListRemote.map(localNotesState.data)
            )
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }
        emit(result)
    }
}