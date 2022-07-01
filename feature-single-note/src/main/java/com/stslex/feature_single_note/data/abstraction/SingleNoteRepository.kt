package com.stslex.feature_single_note.data.abstraction

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import kotlinx.coroutines.flow.Flow

interface SingleNoteRepository {

    fun getNote(id: Int): Flow<ValueState<NoteEntity>>

    suspend fun insertNote(note: NoteUI)
}