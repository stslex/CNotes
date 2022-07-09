package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import org.junit.Assert
import org.junit.Test

class MapperNoteRemoteTest {

    @Test
    fun map() {
        val mapperNote: Mapper.Data<NoteEntity, Map<String, Any>> = MapperNoteRemote()
        val note = NoteEntity(1, "title1", "content1")
        val expectedMap = mapOf(
            "id" to note.id,
            "title" to note.title,
            "content" to note.content,
            "timestamp" to note.timestamp
        )
        val actualMap = mapperNote.map(note)
        Assert.assertEquals(expectedMap, actualMap)
    }
}