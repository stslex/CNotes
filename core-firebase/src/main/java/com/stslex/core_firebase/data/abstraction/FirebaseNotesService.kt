package com.stslex.core_firebase.data.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseNotesService {
    suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void>
    val remoteNotes: Flow<ValueState<List<NoteEntity>>>
    suspend fun getRemoteNotes(): ValueState<List<NoteEntity>>
}