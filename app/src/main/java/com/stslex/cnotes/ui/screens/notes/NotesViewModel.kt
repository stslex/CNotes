package com.stslex.cnotes.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.cnotes.data.repository.NoteRepository
import com.stslex.cnotes.ui.mapper.MapperNoteUIPaging
import com.stslex.cnotes.ui.model.NoteUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val noteMapper: MapperNoteUIPaging
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getAllNotes(): StateFlow<PagingData<NoteUIModel>> = noteRepository.getAllNotes()
        .mapLatest(noteMapper::map)
        .cachedIn(scope = viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )
}