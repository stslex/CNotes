package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity

interface MapperEntityDynamicUI : Mapper.Data<NoteEntity, NoteDynamicUI> {

    class Base : MapperEntityDynamicUI {
        override fun map(data: NoteEntity): NoteDynamicUI = with(data) {
            NoteDynamicUI(id, title, content, timestamp)
        }
    }
}