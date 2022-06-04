package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.core_model.mapper.MapperNoteListRemote
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.SynchronizeNotesInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class SynchronizeNotesInteractorImpl(
    private val repository: ProfileRepository,
    private val mapper: MapperNoteListRemote
) : SynchronizeNotesInteractor {

    override suspend fun invoke(): Flow<ValueState<Void>> = flow {
        repository.getLocalNotes().collectLatest { localNotesState ->
            val result = when (localNotesState) {
                is ValueState.Success -> repository.synchronizeNotes(mapper.map(localNotesState.data))
                is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
                is ValueState.Loading -> ValueState.Loading
            }
            emit(result)
        }
    }
}