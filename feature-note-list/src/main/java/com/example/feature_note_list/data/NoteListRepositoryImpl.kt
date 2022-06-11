package com.example.feature_note_list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.auth.FirebaseAuth
import com.stslex.core_data_source.NoteDao
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteListRepositoryImpl(private val noteDao: NoteDao) : NoteListRepository {

    override val allNotes: Flow<PagingData<NoteEntity>>
        get() = Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = noteDao::getAllNotes
        ).flow

    override val isUserAuth: Boolean
        get() = FirebaseAuth.getInstance().currentUser != null

    override suspend fun deleteNotesById(ids: List<Int>) {
        noteDao.deleteNotesById(ids)
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
    }
}