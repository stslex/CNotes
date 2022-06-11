package com.stslex.feature_profile.data

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun signOut()

    val localNotes: Flow<List<NoteEntity>>

    val localNotesSize: Flow<Int>

    fun getRemoteNotes(): Flow<ValueState<List<NoteEntity>>>

    fun uploadNotesToLocalDB(notes: List<NoteEntity>): Flow<ValueState<Unit>>

    suspend fun uploadNotesToRemoteDB(notes: List<NoteEntity>): Flow<ValueState<Unit>>
}