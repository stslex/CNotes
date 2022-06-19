package com.stslex.core_remote_data_source.service.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseNotesService {
    suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void>
    suspend fun getRealtimeRemoteNotes(): Flow<ValueState<List<NoteEntity>>>
    suspend fun getRemoteNotes(): ValueState<List<NoteEntity>>
}