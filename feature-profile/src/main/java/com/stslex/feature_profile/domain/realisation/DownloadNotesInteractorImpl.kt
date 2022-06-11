package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.DownloadNotesInteractor

class DownloadNotesInteractorImpl(
    private val repository: ProfileRepository
) : DownloadNotesInteractor {

    override suspend fun invoke(): ValueState<Unit> =
        when (val localNotesState = repository.getLocalNotes()) {
            is ValueState.Success -> when (val remoteNotesState = repository.getRemoteNotes()) {
                is ValueState.Success -> {
                    val notesToWrite = remoteNotesState.data.filterNot {
                        localNotesState.data.contains(it)
                    }
                    repository.uploadNotesToLocalDatabase(notesToWrite)
                }
                is ValueState.Failure -> ValueState.Failure(remoteNotesState.exception)
                is ValueState.Loading -> ValueState.Loading
            }
            is ValueState.Failure -> ValueState.Failure(localNotesState.exception)
            is ValueState.Loading -> ValueState.Loading
        }
}