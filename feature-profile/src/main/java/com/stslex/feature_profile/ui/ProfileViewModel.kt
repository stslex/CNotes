package com.stslex.feature_profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.feature_profile.data.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch(dispatchers.io) {
            repository.signOut()
        }
    }

    fun getSyncNotesSize() {

    }

    fun synchronizeNotes() {

    }
}