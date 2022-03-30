package com.stslex.cnotes.core

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.core.Mapper
import java.util.concurrent.Executors

class PagingDataMapper<F : Any, T : Any>(
    private val mapper: Mapper.Data<F, T>
) : Mapper.Data<PagingData<F>, PagingData<T>> {

    override fun map(data: PagingData<F>): PagingData<T> =
        data.map(Executors.newSingleThreadExecutor(), mapper::map)
}

