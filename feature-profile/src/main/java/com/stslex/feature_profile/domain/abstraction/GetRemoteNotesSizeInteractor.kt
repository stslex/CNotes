package com.stslex.feature_profile.domain.abstraction

import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow

interface GetRemoteNotesSizeInteractor {
    suspend operator fun invoke(): Flow<ValueState<Int>>
}