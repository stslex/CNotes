package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.feature_profile.ui.usecases.abstraction.ProfileNotesUseCase
import com.stslex.feature_profile.ui.usecases.abstraction.ProfileUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase,
    private val profileNotesUseCase: ProfileNotesUseCase,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    init {
        profileUseCase.initFirebaseApp()
    }

    val syncNoteSize: StateFlow<ValueState<Int>>
        get() = channelFlow {
            profileNotesUseCase.syncNotesSize().collectLatest(::trySendBlocking)
            awaitClose { }
        }.flowOn(dispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ValueState.Loading
            )

    val remoteNoteSize: StateFlow<ValueState<Int>>
        get() = channelFlow {
            profileNotesUseCase.remoteNotesSize().collectLatest(::trySendBlocking)
            awaitClose { }
        }.flowOn(dispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ValueState.Loading
            )

    val localNotesSize: StateFlow<ValueState<Int>>
        get() = profileNotesUseCase.localNotesSize
            .flowOn(dispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = ValueState.Loading
            )

    fun signOut() {
        viewModelScope.launch(dispatchers.io) {
            profileUseCase.signOut()
        }
    }

    private var syncNotesJob: Job = Job()

    fun uploadNotes() {
        syncNotesJob.cancel()
        syncNotesJob = viewModelScope.launch(dispatchers.io) {
            profileNotesUseCase.uploadNotes().collect()
        }
    }

    private var downloadNotesJob: Job = Job()

    fun downloadNotes() {
        downloadNotesJob.cancel()
        downloadNotesJob = viewModelScope.launch(dispatchers.io) {
            profileNotesUseCase.downloadNotes()
        }
    }
}