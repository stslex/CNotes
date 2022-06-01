package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.feature_profile.data.ProfileRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var syncNotesSizeJob: Job = Job()
    private var remoteNotesSizeJob: Job = Job()
    private var syncNotesJob: Job = Job()

    private var _syncNoteSize: MutableSharedFlow<Int> = MutableSharedFlow(1)
    val syncNoteSize: SharedFlow<Int>
        get() = _syncNoteSize
            .asSharedFlow()
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                replay = 1
            )

    private var _remoteNoteSize: MutableSharedFlow<Int> = MutableSharedFlow(1)
    val remoteNoteSize: SharedFlow<Int>
        get() = _remoteNoteSize
            .asSharedFlow()
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                replay = 1
            )

    fun signOut() {
        viewModelScope.launch(dispatchers.io) {
            repository.signOut()
        }
    }

    fun getSyncNotesSize() {
        syncNotesSizeJob.cancel()
        syncNotesSizeJob = viewModelScope.launch(dispatchers.io) {
            repository.getSyncNotesSize().collectLatest { notesSize ->
                _syncNoteSize.emit(notesSize)
            }
        }
    }

    fun synchronizeNotes() {
        syncNotesJob.cancel()
        syncNotesJob = viewModelScope.launch(dispatchers.io) {
            repository.synchronizeNotes()
        }
    }

    fun getRemoteNotesSize() {
        remoteNotesSizeJob.cancel()
        remoteNotesSizeJob = viewModelScope.launch(dispatchers.io) {
            repository.getRemoteNotesSize().collectLatest { notesSize ->
                _remoteNoteSize.emit(notesSize)
            }
        }
    }

    val localNotesSize: StateFlow<Int> = repository.getLocalNotesSize()
        .flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = 0
        )
}