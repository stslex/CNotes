package com.stslex.core_data_source.service.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface LocalNotesService {
    suspend fun uploadNotesToLocalDatabase(notes: List<NoteEntity>): ValueState<Unit>
    suspend fun getLocalNotesRow(): ValueState<List<NoteEntity>>
    val localNotes: Flow<ValueState<List<NoteEntity>>>
    val notesSize: Flow<ValueState<Int>>
}