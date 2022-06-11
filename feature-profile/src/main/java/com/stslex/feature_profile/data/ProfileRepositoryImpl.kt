package com.stslex.feature_profile.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_data_source.NoteDao
import com.stslex.core_firebase.realisation.ListSnapshot
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProfileRepositoryImpl(
    private val noteDao: NoteDao,
    private val reference: DatabaseReference
) : ProfileRepository {

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override val localNotes: Flow<List<NoteEntity>>
        get() = noteDao.getAllNotesFlow()

    override val localNotesSize: Flow<Int>
        get() = noteDao.getNotesSize()

    override fun getRemoteNotes(): Flow<ValueState<List<NoteEntity>>> = callbackFlow {
        val listener = ListSnapshot(::trySendBlocking).invoke(NoteEntity::class)
        reference.addValueEventListener(listener)
        awaitClose { reference.removeEventListener(listener) }
    }

    override fun uploadNotesToLocalDB(notes: List<NoteEntity>): Flow<ValueState<Unit>> = flow {
        val result = try {
            noteDao.insertAllNotes(notes)
            ValueState.Success(Unit)
        } catch (exception: IOException) {
            ValueState.Failure(exception)
        }
        emit(result)
    }

    override suspend fun uploadNotesToRemoteDB(notes: List<NoteEntity>): Flow<ValueState<Unit>> = callbackFlow {
        reference.updateChildren(notes.fla)
    }
}