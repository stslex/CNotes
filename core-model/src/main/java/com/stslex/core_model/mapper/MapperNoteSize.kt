package com.stslex.core_model.mapper

import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity

internal class MapperNoteSize : Mapper.ToUI<List<NoteEntity>, ValueState<Int>> {

    override fun map(exception: Exception): ValueState<Int> = ValueState.Failure(exception)

    override fun map(): ValueState<Int> = ValueState.Loading

    override fun map(data: List<NoteEntity>): ValueState<Int> = ValueState.Success(data.size)
}