package com.stslex.feature_profile.data.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun signOut()
    fun initFirebaseApp()
    suspend fun downloadNotes(): ValueState<Unit>
    suspend fun uploadNotes(): Flow<ValueState<Void>>
    val localNotes: Flow<ValueState<List<NoteEntity>>>
    val localNotesSize: Flow<ValueState<Int>>
    val remoteNotesSize: Flow<ValueState<Int>>
    val syncNotesSize: Flow<ValueState<Int>>
}