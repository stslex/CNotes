package com.stslex.core_model.data

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.core.Mapper
import com.stslex.core_model.entity.NoteEntity
import java.util.concurrent.Executors
import javax.inject.Inject

interface MapperNoteDataPaging : Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> {

    class Base @Inject constructor(
        private val mapper: MapperNoteEntityData
    ) : MapperNoteDataPaging {

        override fun map(data: PagingData<NoteEntity>): PagingData<NoteDataModel> = data.map(
            executor = Executors.newSingleThreadExecutor(),
            transform = mapper::map
        )
    }
}