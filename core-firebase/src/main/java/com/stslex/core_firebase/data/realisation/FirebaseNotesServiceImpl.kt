package com.stslex.core_firebase.data.realisation

import com.google.firebase.database.DatabaseReference
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_firebase.data.abstraction.FirebaseNotesService
import com.stslex.core_firebase.utils.realisation.CoroutineSnapshotHandler
import com.stslex.core_firebase.utils.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.utils.realisation.ListSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseNotesServiceImpl(
    private val coroutinesTaskHandler: CoroutinesTaskHandler<Void>,
    private val reference: Lazy<DatabaseReference>
) : FirebaseNotesService {

    override suspend fun uploadNotesToRemoteDatabase(updateMap: Map<String, Any>): ValueState<Void> =
        coroutinesTaskHandler(reference.value.updateChildren(updateMap))

    override val remoteNotes: Flow<ValueState<List<NoteEntity>>>
        get() = callbackFlow {
            val listener = ListSnapshot(::trySendBlocking).invoke(NoteEntity::class)
            reference.value.addValueEventListener(listener)
            awaitClose { reference.value.removeEventListener(listener) }
        }

    override suspend fun getRemoteNotes(): ValueState<List<NoteEntity>> =
        CoroutineSnapshotHandler<NoteEntity>().invoke(
            task = reference.value.get(),
            type = NoteEntity::class
        )
}