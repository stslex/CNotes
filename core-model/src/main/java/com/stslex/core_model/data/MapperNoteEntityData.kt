package com.stslex.core_model.data

import com.stslex.core.Mapper
import com.stslex.core_model.entity.NoteEntity
import javax.inject.Inject

interface MapperNoteEntityData : Mapper.Data<NoteEntity, NoteDataModel> {

    class Base @Inject constructor() : MapperNoteEntityData {

        override fun map(data: NoteEntity): NoteDataModel = with(data) {
            NoteDataModel.Base(
                id = id,
                title = title,
                content = content,
                timestamp = timestamp
            )
        }
    }
}