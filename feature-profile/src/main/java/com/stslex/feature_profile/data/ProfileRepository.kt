package com.stslex.feature_profile.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_data_source.NoteDao
import com.stslex.core_firebase.FirebaseReferences
import com.stslex.core_firebase.realisation.CoroutineSnapshotHandler
import com.stslex.core_firebase.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.realisation.ListSnapshot
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface ProfileRepository {

    suspend fun signOut()
    suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void>
    suspend fun uploadNotesToLocalDatabase(notes: List<NoteEntity>): ValueState<Unit>
    suspend fun getRealtimeRemoteNotes(): Flow<ValueState<List<NoteEntity>>>
    suspend fun getRemoteNotes(): ValueState<List<NoteEntity>>
    suspend fun getLocalNotes(): ValueState<List<NoteEntity>>
    fun getLocalNotesSize(): Flow<Int>
    fun isUserAuth(): ValueState<Boolean>

    class Base(
        private val noteDao: NoteDao,
        private val firebaseDatabase: FirebaseDatabase
    ) : ProfileRepository {

        override suspend fun signOut() {
            Firebase.auth.signOut()
        }

        override suspend fun uploadNotesToRemoteDatabase(
            updateMap: Map<String, Any>
        ): ValueState<Void> = CoroutinesTaskHandler<Void>(
            reference.updateChildren(updateMap)
        ).invoke()

        override suspend fun uploadNotesToLocalDatabase(notes: List<NoteEntity>): ValueState<Unit> =
            try {
                noteDao.insertAllNotes(notes)
                ValueState.Success(Unit)
            } catch (exception: IOException) {
                ValueState.Failure(exception)
            }

        override fun getLocalNotesSize(): Flow<Int> = noteDao.getNotesSize()

        override fun isUserAuth(): ValueState<Boolean> =
            ValueState.Success(Firebase.auth.currentUser != null)

        override suspend fun getRealtimeRemoteNotes(): Flow<ValueState<List<NoteEntity>>> =
            callbackFlow {
                val listener = ListSnapshot(::trySendBlocking).invoke(NoteEntity::class)
                reference.addValueEventListener(listener)
                awaitClose { reference.removeEventListener(listener) }
            }

        override suspend fun getRemoteNotes(): ValueState<List<NoteEntity>> =
            CoroutineSnapshotHandler<NoteEntity>().invoke(
                task = reference.get(),
                type = NoteEntity::class
            )

        override suspend fun getLocalNotes(): ValueState<List<NoteEntity>> =
            suspendCoroutine { continuation ->
                try {
                    val result = noteDao.getAllNotesRow()
                    continuation.resume(ValueState.Success(result))
                } catch (exception: IOException) {
                    continuation.resume(ValueState.Failure(exception))
                }
            }

        private val reference: DatabaseReference by lazy {
            firebaseDatabase.reference
                .child(FirebaseReferences.NODE_USERS)
                .child(Firebase.auth.currentUser?.uid ?: "")
                .child(FirebaseReferences.CHILD_NOTES)
        }
    }
}