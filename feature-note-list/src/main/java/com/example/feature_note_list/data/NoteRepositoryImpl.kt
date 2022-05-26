package com.example.feature_note_list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.core_data_source.database.NoteDao
import com.stslex.core_model.data.MapperNoteDataPaging
import com.stslex.core_model.data.NoteDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: MapperNoteDataPaging
) : NoteRepository {

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