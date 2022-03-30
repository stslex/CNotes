package com.stslex.cnotes.data.mapper

import com.stslex.cnotes.data.entity.NoteEntity
import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.cnotes.data.model.NoteDataModelImpl
import com.stslex.core.Mapper

class MapperNoteEntityToData : Mapper.Data<NoteEntity, NoteDataModel> {

    override fun map(data: NoteEntity): NoteDataModel = with(data) {
        NoteDataModelImpl(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}