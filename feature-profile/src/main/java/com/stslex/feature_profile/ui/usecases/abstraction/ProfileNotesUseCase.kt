package com.stslex.feature_profile.ui.usecases.abstraction

import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow

interface ProfileNotesUseCase {
    suspend fun downloadNotes(): ValueState<Unit>
    val localNotesSize: Flow<ValueState<Int>>
    suspend fun remoteNotesSize(): Flow<ValueState<Int>>
    suspend fun syncNotesSize(): Flow<ValueState<Int>>
    suspend fun uploadNotes(): Flow<ValueState<Void>>
}