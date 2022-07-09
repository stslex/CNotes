package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core_model.common.TimeUtil
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity

internal class MapperEntityDynamicUI(
    private val timeUtil: TimeUtil
) : Mapper.Data<NoteEntity, NoteDynamicUI> {

    override fun map(data: NoteEntity): NoteDynamicUI = with(data) {
        NoteDynamicUI(
            id = id,
            title = title,
            content = content,
            timestamp = timeUtil.currentHour(timestamp)
        )
    }
}