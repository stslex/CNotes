package com.stslex.core_model.ui

import com.stslex.core.Mapper
import com.stslex.core_model.data.NoteDataModel
import javax.inject.Inject

interface MapperNoteDataUI : Mapper.Data<NoteDataModel, NoteUIModel> {

    class Base @Inject constructor() : MapperNoteDataUI {

        override fun map(data: NoteDataModel): NoteUIModel = with(data) {
            NoteUIModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}