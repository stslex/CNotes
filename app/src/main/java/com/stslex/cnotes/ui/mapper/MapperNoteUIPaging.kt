package com.stslex.cnotes.ui.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.core.Mapper
import java.util.concurrent.Executors
import javax.inject.Inject

interface MapperNoteUIPaging : Mapper.Data<PagingData<NoteDataModel>, PagingData<NoteUIModel>> {

    class Base @Inject constructor(
        private val mapper: MapperNoteUI
    ) : MapperNoteUIPaging {

        override fun map(data: PagingData<NoteDataModel>): PagingData<NoteUIModel> = data.map(
            executor = Executors.newSingleThreadExecutor(),
            transform = mapper::map
        )
    }
}