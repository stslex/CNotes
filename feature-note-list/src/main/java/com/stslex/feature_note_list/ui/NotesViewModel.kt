package com.stslex.feature_note_list.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.core.AppDispatchers
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.feature_note_list.data.abstraction.NoteListRepository
import com.stslex.feature_note_list.navigation.NoteListRouter
import com.stslex.feature_note_list.ui.core.UIObjectsExt.clearSelection
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteListRepository,
    private val noteMapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>,
    private val dispatchers: AppDispatchers,
    private val router: NoteListRouter
) : ViewModel() {

    private var deleteNotesJob: Job? = null

    val selectedNotes: SnapshotStateList<NoteDynamicUI> = mutableStateListOf()

    val isCreateButtonVisible: MutableState<Boolean> = mutableStateOf(true)

    val allNotes: StateFlow<PagingData<NoteDynamicUI>> = noteRepository.allNotes
        .mapLatest(noteMapper::map)
        .cachedIn(viewModelScope)
        .flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started =
            SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )

    fun onCreateButtonClicked() {
        selectedNotes.apply {
            if (isNotEmpty()) {
                deleteNotesById(map { it.id })
                clearSelection()
            } else {
                isCreateButtonVisible.value = false
                openSingleNote(-1)
            }
        }
    }

    private fun deleteNotesById(ids: List<Int>) {
        deleteNotesJob?.cancel()
        deleteNotesJob = viewModelScope.launch(context = dispatchers.io) {
            noteRepository.deleteNotesById(ids)
        }
    }

    val isUserAuth: Boolean
        get() = noteRepository.isUserAuth

    val openSingleNote: (noteId: Int) -> Unit
        get() = router.openSingleNote

    val openProfile: () -> Unit
        get() = router.openProfile

    val openAuthPhoneNumber: () -> Unit
        get() = router.openAuthPhoneNumber
}