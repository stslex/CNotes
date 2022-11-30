package com.stslex.feature_note_list.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.stslex.core.AppDispatchers
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_ui.base.BaseViewModel
import com.stslex.feature_note_list.data.abstraction.NoteListRepository
import com.stslex.feature_note_list.navigation.NoteListRouter
import com.stslex.feature_note_list.ui.core.UIObjectsExt.clearSelection
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteListRepository,
    private val noteMapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>,
    private val dispatchers: AppDispatchers,
    private val router: NoteListRouter
) : BaseViewModel(dispatchers = dispatchers) {

    private var deleteNotesJob: Job? = null

    val selectedNotes: SnapshotStateList<NoteDynamicUI> = mutableStateListOf()

    val isCreateButtonVisible: MutableState<Boolean> = mutableStateOf(true)

    val allNotes: StateFlow<PagingData<NoteDynamicUI>>
        get() = noteRepository.allNotes
            .mapLatest(noteMapper::map)
            .pagingStateFlow

    fun onCreateButtonClicked() {
        selectedNotes.apply {
            if (isNotEmpty()) {
                deleteNotesById(map { it.id })
                clearSelection()
            } else {
                isCreateButtonVisible.value = false
                router.openSingleNote(-1)
            }
        }
    }

    private fun deleteNotesById(ids: List<Int>) {
        deleteNotesJob?.cancel()
        deleteNotesJob = viewModelScope.launch(
            context = dispatchers.io + coroutineExceptionHandler
        ) {
            noteRepository.deleteNotesById(ids)
        }
    }

    fun onSingleNoteClick(note: NoteDynamicUI) {
        if (selectedNotes.isNotEmpty()) {
            onNotesSelect(note)
        } else {
            isCreateButtonVisible.value = false
            router.openSingleNote(note.id)
        }
    }

    fun onNotesSelect(note: NoteDynamicUI) {
        note.setSelect(!note.isSelect().value)
        if (selectedNotes.contains(note)) {
            selectedNotes.remove(note)
        } else selectedNotes.add(note)
    }

    fun onProfileButtonClicked() {
        if (noteRepository.isUserAuth) {
            router.openProfile()
        } else {
            router.openAuthPhoneNumber()
        }
    }
}