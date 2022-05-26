package com.stslex.feature_single_note.data

import com.stslex.core.ValueState
import com.stslex.core_data_source.database.NoteDao
import com.stslex.core_model.data.MapperNoteDataEntity
import com.stslex.core_model.data.MapperNoteEntityData
import com.stslex.core_model.data.NoteDataModel
import com.stslex.core_model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

interface NoteRepository {

    fun getNote(id: Int): Flow<ValueState<NoteDataModel>>

    suspend fun insertNote(note: NoteDataModel)

    class Base @Inject constructor(
        private val noteDao: NoteDao,
        private val mapperEntityData: MapperNoteEntityData,
        private val mapperDataEntity: MapperNoteDataEntity
    ) : NoteRepository {

        override fun getNote(id: Int): Flow<ValueState<NoteDataModel>> = flow {
            try {
                val result = if (id == -1) NoteDataModel.Base() else {
                    val noteEntity: NoteEntity = noteDao.getNoteById(id)
                    mapperEntityData.map(noteEntity)
                }
                emit(ValueState.Success(result))
            } catch (exception: IOException) {
                emit(ValueState.Failure(exception))
            }
        }

        override suspend fun insertNote(note: NoteDataModel) {
            noteDao.insertNote(mapperDataEntity.map(note))
        }
    }
}