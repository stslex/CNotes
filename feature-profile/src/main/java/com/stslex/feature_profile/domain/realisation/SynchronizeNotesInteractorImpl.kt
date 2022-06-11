package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.core_model.mapper.MapperNoteListRemote
import com.stslex.feature_profile.data.ProfileRepositoryOld
import com.stslex.feature_profile.domain.abstraction.SynchronizeNotesInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SynchronizeNotesInteractorImpl(
    private val repository: ProfileRepositoryOld,
    private val mapper: MapperNoteListRemote
) : SynchronizeNotesInteractor {

    override suspend fun invoke(): Flow<ValueState<Void>> = flow {
        val result = when (val localNotesState = repository.getLocalNotes()) {
            is ValueState.Success -> repository.uploadNotesToRemoteDatabase(
                mapper.map(localNotesState.data)
            )
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }
        emit(result)
    }
}