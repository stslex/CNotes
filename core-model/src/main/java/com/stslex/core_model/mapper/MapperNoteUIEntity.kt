package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI

class MapperNoteUIEntity : Mapper.Data<NoteUI, NoteEntity> {

    override fun map(data: NoteUI): NoteEntity = with(data) {
        NoteEntity(
            id = id,
            title = getTitle().value,
            content = getContent().value,
            timestamp = timestamp
        )
    }
}