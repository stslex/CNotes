package com.stslex.cnotes.ui.mapper

import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.cnotes.ui.model.NoteUIModelImpl
import com.stslex.core.Mapper

class MapperNoteDataToUI : Mapper.Data<NoteDataModel, NoteUIModel> {

    override fun map(data: NoteDataModel): NoteUIModel = with(data) {
        NoteUIModelImpl(
            id = id(),
            title = title(),
            content = content(),
            timestamp = timestamp()
        )
    }
}