package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import org.junit.Test
import kotlin.test.assertEquals

class MapperNoteListRemoteTest {

    @Test
    fun map() {
        val mapperNote: Mapper.Data<NoteEntity, Map<String, Any>> = MapperNoteRemote()
        val mapperList: Mapper.Data<List<NoteEntity>, Map<String, Any>> =
            MapperNoteListRemote(mapperNote)
        val noteFirst = NoteEntity(1, "title1", "content1")
        val noteSecond = NoteEntity(2, "title2", "content2")
        val listRemote = listOf(noteFirst, noteSecond)
        val expectedMap = mapOf(
            noteFirst.id.toString() to mapOf(
                "id" to noteFirst.id,
                "title" to noteFirst.title,
                "content" to noteFirst.content,
                "timestamp" to noteFirst.timestamp
            ),
            noteSecond.id.toString() to mapOf(
                "id" to noteSecond.id,
                "title" to noteSecond.title,
                "content" to noteSecond.content,
                "timestamp" to noteSecond.timestamp
            )
        )
        val actualMap = mapperList.map(listRemote)
        assertEquals(expectedMap, actualMap)
    }
}