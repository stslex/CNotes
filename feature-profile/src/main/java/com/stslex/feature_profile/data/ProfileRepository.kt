package com.stslex.feature_profile.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_data_source.NoteDao
import com.stslex.core_firebase.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.realisation.ListSnapshot
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

interface ProfileRepository {

    suspend fun signOut()
    suspend fun synchronizeNotes(updateMap: Map<String, Any>): ValueState<Void>
    suspend fun getRemoteNotes(): Flow<ValueState<List<NoteEntity>>>
    fun getLocalNotes(): Flow<ValueState<List<NoteEntity>>>
    fun getLocalNotesSize(): Flow<Int>

    class Base(
        private val noteDao: NoteDao,
        private val reference: DatabaseReference
    ) : ProfileRepository {

        override suspend fun signOut() {
            Firebase.auth.signOut()
        }

        override suspend fun synchronizeNotes(
            updateMap: Map<String, Any>
        ): ValueState<Void> = CoroutinesTaskHandler<Void>(
            reference.updateChildren(updateMap)
        ).invoke()


        override fun getLocalNotesSize(): Flow<Int> = noteDao.getNotesSize()

        override suspend fun getRemoteNotes(): Flow<ValueState<List<NoteEntity>>> = callbackFlow {
            val listener = ListSnapshot(::trySendBlocking).invoke(NoteEntity::class)
            reference.addValueEventListener(listener)
            awaitClose { reference.removeEventListener(listener) }
        }

        override fun getLocalNotes(): Flow<ValueState<List<NoteEntity>>> = flow {
            val result = try {
                ValueState.Success(noteDao.getAllNotesRow())
            } catch (exception: Exception) {
                ValueState.Failure(exception)
            }
            emit(result)
        }
    }
}