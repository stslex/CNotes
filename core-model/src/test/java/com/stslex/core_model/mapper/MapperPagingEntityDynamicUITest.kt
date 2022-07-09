package com.stslex.core_model.mapper

import androidx.paging.PagingData
import androidx.paging.filter
import com.stslex.core.Mapper
import com.stslex.core_model.common.TimeUtil
import com.stslex.core_model.common.TimeUtilImpl
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import org.junit.Test
import kotlin.test.assertNotEquals

class MapperPagingEntityDynamicUITest {

    @Test
    fun map() {
        val timeUtil: TimeUtil = TimeUtilImpl()
        val mapperSingle: Mapper.Data<NoteEntity, NoteDynamicUI> = MapperEntityDynamicUI(timeUtil)
        val mapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>> =
            MapperPagingEntityDynamicUI(mapperSingle)

        val noteList = listOf(NoteEntity(id = 323))
        val actualData = mapper.map(PagingData.from(noteList))

        val equals = actualData.filter {
            it.id == noteList.first().id
        }
        assertNotEquals(equals, PagingData.empty())
    }
}