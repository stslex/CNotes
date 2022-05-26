package com.stslex.core_model.ui

import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.data.NoteDataModel
import javax.inject.Inject

interface MapperNoteValueDataUI : Mapper.ToUI<NoteDataModel, ValueState<NoteUIModel>> {

    class Base @Inject constructor(
        private val mapper: MapperNoteDataUI
    ) : MapperNoteValueDataUI {

        override fun map(exception: Exception): ValueState<NoteUIModel> =
            ValueState.Failure(exception)

        override fun map(): ValueState<NoteUIModel> = ValueState.Loading

        override fun map(data: NoteDataModel): ValueState<NoteUIModel> =
            ValueState.Success(mapper.map(data))
    }
}