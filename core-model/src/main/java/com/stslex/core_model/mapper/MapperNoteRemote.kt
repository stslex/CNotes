package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity

interface MapperNoteRemote : Mapper.Data<NoteEntity, Map<String, Any>> {

    class Base : MapperNoteRemote {

        override fun map(data: NoteEntity): Map<String, Any> = with(data) {
            mapOf(
                "id" to id,
                "title" to title,
                "content" to content,
                "timestamp" to timestamp
            )
        }
    }
}