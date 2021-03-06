package com.stslex.feature_note_list.data.abstraction

import androidx.paging.PagingData
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteListRepository {

    val allNotes: Flow<PagingData<NoteEntity>>

    suspend fun deleteNotesById(ids: List<Int>)

    val isUserAuth: Boolean
}