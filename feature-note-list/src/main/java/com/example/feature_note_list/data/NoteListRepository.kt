package com.example.feature_note_list.data

import androidx.paging.PagingData
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteListRepository {

    val allNotes: Flow<PagingData<NoteEntity>>

    suspend fun deleteNotesById(ids: List<Int>)
}