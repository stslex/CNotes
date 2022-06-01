package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity

interface MapperNoteListRemote : Mapper.Data<List<NoteEntity>, Map<String, Any>> {

    class Base(
        private val mapper: MapperNoteRemote
    ) : MapperNoteListRemote {

        override fun map(data: List<NoteEntity>): Map<String, Any> = data
            .map(mapper::map)
            .associateBy { it["id"].toString() }
    }
}
