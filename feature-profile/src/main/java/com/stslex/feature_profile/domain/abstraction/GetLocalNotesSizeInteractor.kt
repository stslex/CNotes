package com.stslex.feature_profile.domain.abstraction

import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow

interface GetLocalNotesSizeInteractor {
    fun invoke(): Flow<ValueState<Int>>
}