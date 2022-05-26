package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import javax.inject.Inject

class MapperNoteUIEntity @Inject constructor() : Mapper.Data<NoteUI, NoteEntity> {

    override fun map(data: NoteUI): NoteEntity = with(data) {
        NoteEntity(id, title, content, timestamp)
    }
}