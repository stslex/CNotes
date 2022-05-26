package com.stslex.core_model.ui

import com.stslex.core.Mapper
import com.stslex.core_model.data.NoteDataModel
import javax.inject.Inject

interface MapperNoteUIData : Mapper.Data<NoteUIModel, NoteDataModel> {

    class Base @Inject constructor() : MapperNoteUIData {

        override fun map(data: NoteUIModel): NoteDataModel = with(data) {
            NoteDataModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}