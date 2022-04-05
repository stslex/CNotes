package com.stslex.cnotes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.cnotes.data.database.NoteDao
import com.stslex.cnotes.data.mapper.MapperNoteDataPaging
import com.stslex.cnotes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: MapperNoteDataPaging
) : NoteRepository {

    @ExperimentalCoroutinesApi
    override val allNotes: Flow<PagingData<NoteDataModel>>
        get() = Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = noteDao::getAllNotes
        ).flow.mapLatest(mapper::map)

    override suspend fun deleteNotesById(ids: List<Int>) {
        noteDao.deleteNotesById(ids)
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
    }
}