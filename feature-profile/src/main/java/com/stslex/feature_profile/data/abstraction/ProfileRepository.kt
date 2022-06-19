package com.stslex.feature_profile.data.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun signOut()
    suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void>
    suspend fun uploadNotesToLocalDatabase(notes: List<NoteEntity>): ValueState<Unit>
    suspend fun getRealtimeRemoteNotes(): Flow<ValueState<List<NoteEntity>>>
    suspend fun getRemoteNotes(): ValueState<List<NoteEntity>>
    suspend fun getLocalNotes(): ValueState<List<NoteEntity>>
    val localNotesSize: Flow<ValueState<Int>>
    fun initFirebaseApp()
}