package com.stslex.core_model.mapper

import androidx.compose.runtime.mutableStateOf
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI

interface MapperEntityUI : Mapper.Data<NoteEntity, NoteUI> {

    class Base : MapperEntityUI {

        override fun map(data: NoteEntity): NoteUI = with(data) {
            NoteUI(
                id = id,
                title = mutableStateOf(title), mutableStateOf(content),
                timestamp
            )
        }
    }
}