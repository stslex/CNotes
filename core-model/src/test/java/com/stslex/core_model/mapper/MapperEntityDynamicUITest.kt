package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.common.TimeUtil
import com.stslex.core_model.common.TimeUtilImpl
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import org.junit.Assert
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
        Assert.assertEquals(expectedNote, actualNote)
    }
}