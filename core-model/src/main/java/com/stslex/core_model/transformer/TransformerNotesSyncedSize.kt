package com.stslex.core_model.transformer

import com.stslex.core.Transformer
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity

interface TransformerNotesSyncedSize : Transformer.ToUI<List<NoteEntity>, List<NoteEntity>, ValueState<Int>> {

    class Base : TransformerNotesSyncedSize {

        override fun transform(
            firstData: List<NoteEntity>,
            secondData: List<NoteEntity>
        ): ValueState<Int> = ValueState.Success(firstData.filter { localNote ->
            secondData.contains(localNote)
        }.size)

        override fun transform(exception: Exception): ValueState<Int> = ValueState.Failure(exception)

        override fun transform(): ValueState<Int> = ValueState.Loading
    }
}