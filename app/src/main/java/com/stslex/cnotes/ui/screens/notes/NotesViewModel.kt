package com.stslex.cnotes.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.cnotes.data.repository.NoteRepository
import com.stslex.cnotes.ui.mapper.MapperNoteUIPaging
import com.stslex.cnotes.ui.model.NoteUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    noteRepository: NoteRepository,
    private val noteMapper: MapperNoteUIPaging
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val allNotes: StateFlow<PagingData<NoteUIModel>> = noteRepository.allNotes
        .mapLatest(noteMapper::map)
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}