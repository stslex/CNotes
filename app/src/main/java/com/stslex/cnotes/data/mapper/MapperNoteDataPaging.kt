package com.stslex.cnotes.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.cnotes.data.entity.NoteEntity
import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.core.Mapper
import java.util.concurrent.Executors
import javax.inject.Inject

interface MapperNoteDataPaging : Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> {

    class Base @Inject constructor(
        private val mapper: MapperNoteData
    ) : MapperNoteDataPaging {

        override fun map(data: PagingData<NoteEntity>): PagingData<NoteDataModel> = data.map(
            executor = Executors.newSingleThreadExecutor(),
            transform = mapper::map
        )
    }
}