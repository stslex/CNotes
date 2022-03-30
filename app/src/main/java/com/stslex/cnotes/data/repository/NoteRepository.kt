package com.stslex.cnotes.data.repository

import androidx.paging.PagingData
import com.stslex.cnotes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    @ExperimentalCoroutinesApi
    suspend fun getAllNotes(): Flow<PagingData<NoteDataModel>>
}