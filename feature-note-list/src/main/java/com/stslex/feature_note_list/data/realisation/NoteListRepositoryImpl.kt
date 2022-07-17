package com.stslex.feature_note_list.data.realisation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.feature_note_list.data.abstraction.NoteListRepository
import com.google.firebase.auth.FirebaseAuth
import com.stslex.core_data_source.dao.NoteDao
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteListRepositoryImpl(
    private val noteDao: NoteDao,
    private val pagingConfig: PagingConfig
) : NoteListRepository {

    override val allNotes: Flow<PagingData<NoteEntity>>
        get() = Pager(config = pagingConfig, pagingSourceFactory = noteDao::getAllNotes).flow

    override val isUserAuth: Boolean
        get() = FirebaseAuth.getInstance().currentUser != null

    override suspend fun deleteNotesById(ids: List<Int>) {
        noteDao.deleteNotesById(ids)
    }
}