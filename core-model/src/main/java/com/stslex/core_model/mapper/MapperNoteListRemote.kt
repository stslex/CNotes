package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity

internal class MapperNoteListRemote(
    private val mapper: Mapper.Data<NoteEntity, Map<String, Any>>
) : Mapper.Data<List<NoteEntity>, Map<String, Any>> {

    override fun map(data: List<NoteEntity>): Map<String, Any> = data
        .map(mapper::map)
        .associateBy { it["id"].toString() }
}
