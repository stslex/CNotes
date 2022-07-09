package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import org.junit.Test
import kotlin.test.assertTrue

class MapperNoteSizeTest {

    private val mapper: Mapper.ToUI<List<NoteEntity>, ValueState<Int>> by lazy {
        MapperNoteSize()
    }

    @Test
    fun map() {
        val list = listOf(NoteEntity(), NoteEntity(), NoteEntity())
        val expectedSize = list.size
        assertTrue {
            when (val actualValue = mapper.map(list)) {
                is ValueState.Success -> expectedSize == actualValue.data
                is ValueState.Failure -> false
                is ValueState.Loading -> false
            }
        }
    }

    @Test
    fun mapLoading() {
        assertTrue {
            when (mapper.map()) {
                is ValueState.Success -> false
                is ValueState.Failure -> false
                is ValueState.Loading -> true
            }
        }
    }

    @Test
    fun mapFailure() {
        assertTrue {
            when (mapper.map(Exception())) {
                is ValueState.Success -> false
                is ValueState.Failure -> true
                is ValueState.Loading -> false
            }
        }
    }
}