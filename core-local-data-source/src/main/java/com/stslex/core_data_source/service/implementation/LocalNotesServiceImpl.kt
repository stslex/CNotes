package com.stslex.core_data_source.service.implementation

import com.stslex.core.ValueState
import com.stslex.core_data_source.dao.NoteDao
import com.stslex.core_data_source.service.abstraction.LocalNotesService
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapNotNull
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalNotesServiceImpl(private val noteDao: NoteDao) : LocalNotesService {

    override suspend fun uploadNotesToLocalDatabase(
        notes: List<NoteEntity>
    ): ValueState<Unit> = try {
        noteDao.insertAllNotes(notes)
        ValueState.Success(Unit)
    } catch (exception: IOException) {
        ValueState.Failure(exception)
    }

    override suspend fun getLocalNotesRow(): ValueState<List<NoteEntity>> =
        suspendCoroutine { continuation ->
            try {
                val result = noteDao.getAllNotesRow()
                continuation.resume(ValueState.Success(result))
            } catch (exception: IOException) {
                continuation.resume(ValueState.Failure(exception))
            }
        }

    override val localNotes: Flow<ValueState<List<NoteEntity>>>
        get() = try {
            noteDao.getAllNotesFlow().mapNotNull { ValueState.Success(it) }
        } catch (exception: IOException) {
            flowOf(ValueState.Failure(exception))
        }

    override val notesSize: Flow<ValueState<Int>>
        get() = try {
            noteDao.getNotesSize().flatMapLatest { flowOf(ValueState.Success(it)) }
        } catch (exception: Exception) {
            flowOf(ValueState.Failure(exception))
        }
}