package com.stslex.core_model.ui

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.core.Mapper
import com.stslex.core_model.data.NoteDataModel
import java.util.concurrent.Executors
import javax.inject.Inject

interface MapperNoteDataUIPaging : Mapper.Data<PagingData<NoteDataModel>, PagingData<NoteUIModel>> {

    class Base @Inject constructor(
        private val mapper: MapperNoteDataUI
    ) : MapperNoteDataUIPaging {

        override fun map(data: PagingData<NoteDataModel>): PagingData<NoteUIModel> = data.map(
            executor = Executors.newSingleThreadExecutor(),
            transform = mapper::map
        )
    }
}