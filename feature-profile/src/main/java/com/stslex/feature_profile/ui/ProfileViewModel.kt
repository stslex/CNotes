package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.core_firebase.abstraction.FirebaseAppInitialisationUtil
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
    private val checkUserAuthInteractor: CheckUserAuthInteractor,
    private val dispatchers: AppDispatchers,
    private val downloadNotesInteractor: DownloadNotesInteractor,
    private val firebaseAppInitialisationUtil: FirebaseAppInitialisationUtil,
    private val getLocalNotesSizeInteractor: GetLocalNotesSizeInteractor
) : ViewModel() {

    init {
        firebaseAppInitialisationUtil.invoke()
    }

    val userIsAuth: ValueState<Boolean>
        get() = checkUserAuthInteractor.invoke()

    val syncNoteSize: StateFlow<ValueState<Int>>
        get() = channelFlow {
            getSyncNotesSizeInteractor.invoke().collectLatest(::trySendBlocking)
            awaitClose { }
        }.flowOn(dispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ValueState.Loading
            )

    val remoteNoteSize: StateFlow<ValueState<Int>>
        get() = channelFlow {
            getRemoteNotesSizeInteractor.invoke().collectLatest(::trySendBlocking)
            awaitClose { }
        }.flowOn(dispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ValueState.Loading
            )

    val localNotesSize: StateFlow<ValueState<Int>>
        get() = getLocalNotesSizeInteractor.invoke()
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

    fun uploadNotes() {
        syncNotesJob.cancel()
        syncNotesJob = viewModelScope.launch(dispatchers.io) {
            synchronizeNotesInteractor.invoke().collect()
        }
    }

    private var downloadNotesJob: Job = Job()

    fun downloadNotes() {
        downloadNotesJob.cancel()
        downloadNotesJob = viewModelScope.launch(dispatchers.io) {
            downloadNotesInteractor.invoke()
        }
    }
}