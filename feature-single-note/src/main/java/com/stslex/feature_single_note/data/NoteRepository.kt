package com.stslex.feature_single_note.data

import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_data_source.database.NoteDao
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

interface NoteRepository {

    fun getNote(id: Int): Flow<ValueState<NoteEntity>>

    suspend fun insertNote(note: NoteUI)

    class Base @Inject constructor(
        private val noteDao: NoteDao,
        private val mapper: Mapper.Data<NoteUI, NoteEntity>
    ) : NoteRepository {

        override fun getNote(id: Int): Flow<ValueState<NoteEntity>> = flow {
            try {
                val result = if (id == -1) NoteEntity() else noteDao.getNoteById(id)
                emit(ValueState.Success(result))
            } catch (exception: IOException) {
                emit(ValueState.Failure(exception))
            }
        }

        override suspend fun insertNote(note: NoteUI) {
            noteDao.insertNote(mapper.map(note))
        }
    }
}