package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI

interface MapperNoteUIEntity : Mapper.Data<NoteUI, NoteEntity> {

    class Base : MapperNoteUIEntity {
        override fun map(data: NoteUI): NoteEntity = with(data) {
            NoteEntity(
                id = id,
                title = getTitle().value,
                content = getContent().value,
                timestamp = timestamp
            )
        }
    }
}