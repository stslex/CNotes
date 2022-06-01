package com.stslex.feature_profile.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.stslex.core_data_source.NoteDao
import com.stslex.core_model.mapper.MapperNoteListRemote
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface ProfileRepository {

    suspend fun signOut()
    suspend fun getSyncNotesSize(): Flow<Int>
    suspend fun synchronizeNotes()
    suspend fun getRemoteNotesSize(): Flow<Int>
    fun getLocalNotesSize(): Flow<Int>

    class Base(
        private val noteDao: NoteDao,
        private val mapper: MapperNoteListRemote,
        private val reference: DatabaseReference
    ) : ProfileRepository {

        override suspend fun signOut() {
            Firebase.auth.signOut()
        }

        override suspend fun getSyncNotesSize(): Flow<Int> = callbackFlow {
            val localNoteList = noteDao.getAllNotesRow();
            val listener: ValueEventListener = ListValueEventListener(NoteEntity::class) { list ->
                val resultList = localNoteList.filter { localNote ->
                    list.contains(localNote)
                }
                trySendBlocking(resultList.size)
            }
            reference.addValueEventListener(listener)
            awaitClose { reference.removeEventListener(listener) }
        }

        override suspend fun synchronizeNotes() {
            val currentNoteList = noteDao.getAllNotesRow()
            val mapForUpdate: Map<String, Any> = mapper.map(currentNoteList)
            reference.updateChildren(mapForUpdate)
        }

        override fun getLocalNotesSize(): Flow<Int> = noteDao.getNotesSize()

        override suspend fun getRemoteNotesSize(): Flow<Int> = callbackFlow {
            val listener: ValueEventListener = ListValueEventListener(NoteEntity::class) { list ->
                trySendBlocking(list.size)
            }
            reference.addValueEventListener(listener)
            awaitClose { reference.removeEventListener(listener) }
        }
    }
}