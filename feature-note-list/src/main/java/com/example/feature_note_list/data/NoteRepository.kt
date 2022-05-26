package com.example.feature_note_list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.core_data_source.database.NoteDao
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NoteRepository {

    val allNotes: Flow<PagingData<NoteEntity>>

    suspend fun deleteNotesById(ids: List<Int>)

    class Base @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

        override val allNotes: Flow<PagingData<NoteEntity>>
            get() = Pager(
                config = PagingConfig(PAGE_SIZE),
                pagingSourceFactory = noteDao::getAllNotes
            ).flow

        override suspend fun deleteNotesById(ids: List<Int>) {
            noteDao.deleteNotesById(ids)
        }

        companion object {
            private const val PAGE_SIZE: Int = 10
        }
    }
}