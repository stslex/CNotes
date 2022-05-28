package com.example.feature_note_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.feature_note_list.data.NoteListRepository
import com.stslex.core_model.mapper.MapperPagingEntityDynamicUI
import com.stslex.core_model.model.NoteDynamicUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteListRepository,
    private val noteMapper: MapperPagingEntityDynamicUI
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val allNotes: StateFlow<PagingData<NoteDynamicUI>> = noteRepository.allNotes
        .mapLatest(noteMapper::map)
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun deleteNotesById(ids: List<Int>) {
        viewModelScope.launch(context = Dispatchers.IO) {
            noteRepository.deleteNotesById(ids)
        }
    }
}