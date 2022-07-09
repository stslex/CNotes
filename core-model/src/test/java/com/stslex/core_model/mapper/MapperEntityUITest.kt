package com.stslex.core_model.mapper

import androidx.compose.runtime.mutableStateOf
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperEntityUITest {

    @Test
    fun map() {
        val mapper: Mapper.Data<NoteEntity, NoteUI> = MapperEntityUI()
        val time = System.currentTimeMillis()
        val noteEntity = NoteEntity(2, "title", "content", time)
        val expectedValue = NoteUI(2, mutableStateOf("title"), mutableStateOf("content"), time)
        val actualValue = mapper.map(noteEntity)
        assertEquals(expectedValue.id, actualValue.id)
        assertEquals(expectedValue.getTitle().value, actualValue.getTitle().value)
        assertEquals(expectedValue.getContent().value, actualValue.getContent().value)
        assertEquals(expectedValue.timestamp, actualValue.timestamp)
    }
}