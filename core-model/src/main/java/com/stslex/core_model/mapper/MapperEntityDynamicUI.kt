package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

interface MapperEntityDynamicUI : Mapper.Data<NoteEntity, NoteDynamicUI> {

    class Base : MapperEntityDynamicUI {
        override fun map(data: NoteEntity): NoteDynamicUI = with(data) {
            NoteDynamicUI(
                id = id,
                title = title,
                content = content,
                timestamp = SimpleDateFormat("kk.mm", Locale.getDefault()).format(timestamp)
            )
        }
    }
}