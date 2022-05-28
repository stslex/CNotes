package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI

interface MapperValueNoteEntityUI : Mapper.ToUI<NoteEntity, ValueState<NoteUI>> {

    class Base(
        private val mapper: MapperEntityUI
    ) : MapperValueNoteEntityUI {

        override fun map(data: NoteEntity): ValueState<NoteUI> =
            ValueState.Success(mapper.map(data))

        override fun map(exception: Exception): ValueState<NoteUI> = ValueState.Failure(exception)

        override fun map(): ValueState<NoteUI> = ValueState.Loading
    }
}