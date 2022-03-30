package com.stslex.cnotes.ui.mapper

import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.cnotes.ui.model.NoteUIModelImpl
import com.stslex.core.Mapper
import javax.inject.Inject

interface MapperNoteUI : Mapper.Data<NoteDataModel, NoteUIModel> {

    class Base @Inject constructor() : MapperNoteUI {

        override fun map(data: NoteDataModel): NoteUIModel = with(data) {
            NoteUIModelImpl(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}