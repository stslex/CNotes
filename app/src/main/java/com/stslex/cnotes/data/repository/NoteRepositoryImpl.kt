package com.stslex.cnotes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.cnotes.data.database.NoteDao
import com.stslex.cnotes.data.entity.NoteEntity
import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.core.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>>
) : NoteRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getAllNotes(): Flow<PagingData<NoteDataModel>> = Pager(
        config = PagingConfig(PAGE_SIZE),
        pagingSourceFactory = noteDao::getAllNotes
    ).flow.mapLatest(mapper::map)

    companion object {
        private const val PAGE_SIZE: Int = 10;
    }
}