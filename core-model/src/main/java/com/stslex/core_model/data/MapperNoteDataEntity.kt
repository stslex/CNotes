package com.stslex.core_model.data

import com.stslex.core.Mapper
import com.stslex.core_model.entity.NoteEntity
import javax.inject.Inject

interface MapperNoteDataEntity : Mapper.Data<NoteDataModel, NoteEntity> {

    class Base @Inject constructor() : MapperNoteDataEntity {

        override fun map(data: NoteDataModel): NoteEntity = with(data) {
            NoteEntity(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}