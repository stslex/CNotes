package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import javax.inject.Inject

class MapperEntityUI @Inject constructor() : Mapper.Data<NoteEntity, NoteUI> {

    override fun map(data: NoteEntity): NoteUI = with(data) {
        NoteUI(id, title, content, timestamp)
    }
}