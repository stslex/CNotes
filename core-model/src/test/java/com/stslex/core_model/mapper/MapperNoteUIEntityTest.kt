package com.stslex.core_model.mapper

import androidx.compose.runtime.mutableStateOf
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import org.junit.Test
import kotlin.test.assertEquals

class MapperNoteUIEntityTest {

    @Test
    fun map() {
        val mapper: Mapper.Data<NoteUI, NoteEntity> = MapperNoteUIEntity()
        val time = System.currentTimeMillis()
        val title = mutableStateOf("title")
        val content = mutableStateOf("content")
        val note = NoteUI(1, title, content, time)
        val expectedNote = NoteEntity(1, title.value, content.value, time)
        val actualNote = mapper.map(note)
        assertEquals(expectedNote, actualNote)
    }
}