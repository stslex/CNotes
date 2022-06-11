package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.feature_profile.data.ProfileRepositoryOld
import com.stslex.feature_profile.domain.abstraction.GetLocalNotesSizeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class GetLocalNotesSizeInteractorImpl(
    private val repository: ProfileRepositoryOld
) : GetLocalNotesSizeInteractor {

    override fun invoke(): Flow<ValueState<Int>> = try {
        repository.getLocalNotesSize().flatMapLatest { flowOf(ValueState.Success(it)) }
    } catch (exception: Exception) {
        flowOf(ValueState.Failure(exception))
    }
}