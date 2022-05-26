package com.example.feature_note_list.data

import androidx.paging.PagingData
import com.stslex.core_model.data.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    val allNotes: Flow<PagingData<NoteDataModel>>

    suspend fun deleteNotesById(ids: List<Int>)
}