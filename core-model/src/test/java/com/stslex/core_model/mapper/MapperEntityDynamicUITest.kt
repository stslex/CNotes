package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.common.TimeUtil
import com.stslex.core_model.common.TimeUtilImpl
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperEntityDynamicUITest {

    @Test
    fun map() {
        val timeUtil: TimeUtil = TimeUtilImpl()
        val mapper: Mapper.Data<NoteEntity, NoteDynamicUI> = MapperEntityDynamicUI(timeUtil)
        val time = System.currentTimeMillis()
        val noteEntity = NoteEntity(2, "title", "content", time)
        val expectedNote = NoteDynamicUI(2, "title", "content", timeUtil.currentHour(time))
        val actualNote = mapper.map(noteEntity)
        assertEquals(expectedNote.id, actualNote.id)
        assertEquals(expectedNote.content, actualNote.content)
        assertEquals(expectedNote.title, actualNote.title)
        assertEquals(expectedNote.timestamp, actualNote.timestamp)
        assertEquals(expectedNote.isSelect().value, actualNote.isSelect().value)
    }
}