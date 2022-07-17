package com.stslex.core_remote_data_source.service.implementation

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_firebase.FirebaseReferences
import com.stslex.core_firebase.realisation.CoroutineSnapshotHandler
import com.stslex.core_firebase.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.realisation.ListSnapshot
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_remote_data_source.service.abstraction.FirebaseNotesService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseNotesServiceImpl(
    private val firebaseDatabase: FirebaseDatabase,
    private val coroutinesTaskHandler: CoroutinesTaskHandler<Void>
) : FirebaseNotesService {

    override suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void> =
        coroutinesTaskHandler(reference.updateChildren(updateMap))

    override val remoteNotes: Flow<ValueState<List<NoteEntity>>>
        get() = callbackFlow {
            val listener = ListSnapshot(::trySendBlocking).invoke(NoteEntity::class)
            reference.addValueEventListener(listener)
            awaitClose { reference.removeEventListener(listener) }
        }

    override suspend fun getRemoteNotes(): ValueState<List<NoteEntity>> =
        CoroutineSnapshotHandler<NoteEntity>().invoke(
            task = reference.get(),
            type = NoteEntity::class
        )

    private val reference: DatabaseReference by lazy {
        firebaseDatabase.reference
            .child(FirebaseReferences.NODE_USERS)
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child(FirebaseReferences.CHILD_NOTES)
    }
}