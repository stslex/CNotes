package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.feature_profile.domain.abstraction.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getRemoteNotesSizeInteractor: GetRemoteNotesSizeInteractor,
    private val getSyncNotesSizeInteractor: GetSyncNotesSizeInteractor,
    private val signOutInteractor: SignOutInteractor,
    private val synchronizeNotesInteractor: SynchronizeNotesInteractor,
    private val getLocalNotesSizeInteractor: GetLocalNotesSizeInteractor,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    val syncNoteSize: StateFlow<ValueState<Int>> = channelFlow {
        getSyncNotesSizeInteractor.invoke().collectLatest(::trySendBlocking)
        awaitClose { }
    }.flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    val remoteNoteSize: StateFlow<ValueState<Int>> = channelFlow {
        getRemoteNotesSizeInteractor.invoke().collectLatest(::trySendBlocking)
        awaitClose { }
    }.flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    val localNotesSize: StateFlow<ValueState<Int>> = getLocalNotesSizeInteractor.invoke()
        .flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ValueState.Loading
        )

    fun signOut() {
        viewModelScope.launch(dispatchers.io) {
            signOutInteractor.invoke()
        }
    }

    private var syncNotesJob: Job = Job()

    fun synchronizeNotes() {
        syncNotesJob.cancel()
        syncNotesJob = viewModelScope.launch(dispatchers.io) {
            synchronizeNotesInteractor.invoke().collect()
        }
    }
}