package com.stslex.feature_profile.domain.abstraction

import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow

fun interface GetSyncNotesSizeInteractor {
     suspend operator fun invoke(): Flow<ValueState<Int>>
}