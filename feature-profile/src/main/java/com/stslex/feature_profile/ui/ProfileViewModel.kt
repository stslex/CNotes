package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.ValueState
import com.stslex.core.AppDispatchers
import com.stslex.feature_profile.data.abstraction.ProfileRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    init {
        repository.initFirebaseApp()
    }

    val syncNoteSize: StateFlow<ValueState<Int>>
        get() = repository.syncNotesSize.flowOn(dispatchers.io).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    val remoteNoteSize: StateFlow<ValueState<Int>>
        get() = repository.remoteNotesSize.flowOn(dispatchers.io).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    val localNotesSize: StateFlow<ValueState<Int>>
        get() = repository.localNotesSize.flowOn(dispatchers.io).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    fun signOut() {
        viewModelScope.launch(dispatchers.io) {
            repository.signOut()
        }
    }

    private var syncNotesJob: Job = Job()

    fun uploadNotes() {
        syncNotesJob.cancel()
        syncNotesJob = viewModelScope.launch(dispatchers.io) {
            repository.uploadNotes().collect()
        }
    }

    private var downloadNotesJob: Job = Job()

    fun downloadNotes() {
        downloadNotesJob.cancel()
        downloadNotesJob = viewModelScope.launch(dispatchers.io) {
            repository.downloadNotes()
        }
    }
}