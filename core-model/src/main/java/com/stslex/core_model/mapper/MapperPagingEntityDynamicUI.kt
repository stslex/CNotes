package com.stslex.core_model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import java.util.concurrent.Executors

class MapperPagingEntityDynamicUI(
    private val mapper: Mapper.Data<NoteEntity, NoteDynamicUI>
) : Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>> {

    override fun map(data: PagingData<NoteEntity>): PagingData<NoteDynamicUI> =
        data.map(Executors.newSingleThreadExecutor(), mapper::map)
}