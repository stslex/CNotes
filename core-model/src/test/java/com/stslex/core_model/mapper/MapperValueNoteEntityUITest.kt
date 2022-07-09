package com.stslex.core_model.mapper

import androidx.compose.runtime.mutableStateOf
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import org.junit.Test
import kotlin.test.assertTrue

class MapperValueNoteEntityUITest {

    private val mapperNote: Mapper.Data<NoteEntity, NoteUI> by lazy {
        MapperEntityUI()
    }

    private val mapper: Mapper.ToUI<NoteEntity, ValueState<NoteUI>> by lazy {
        MapperValueNoteEntityUI(mapperNote)
    }

    @Test
    fun map() {
        val noteEntity = NoteEntity(1, "title", "content", System.currentTimeMillis())
        val expectedNote = with(noteEntity) {
            NoteUI(
                id = id,
                title = mutableStateOf(title),
                content = mutableStateOf(content),
                timestamp = timestamp
            )
        }
        assertTrue {
            when (val actualValue = mapper.map(noteEntity)) {
                is ValueState.Success -> with(actualValue.data) {
                    id == expectedNote.id &&
                            getTitle().value == expectedNote.getTitle().value &&
                            getContent().value == expectedNote.getContent().value &&
                            timestamp == noteEntity.timestamp
                }
                is ValueState.Failure -> false
                is ValueState.Loading -> false
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
}