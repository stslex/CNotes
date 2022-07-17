package com.stslex.feature_note_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.core.AppDispatchers
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.feature_note_list.data.abstraction.NoteListRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteListRepository,
    private val noteMapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    val allNotes: StateFlow<PagingData<NoteDynamicUI>> = noteRepository.allNotes
        .mapLatest(noteMapper::map)
        .cachedIn(viewModelScope)
        .flowOn(dispatchers.io)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun deleteNotesById(ids: List<Int>) {
        viewModelScope.launch(context = dispatchers.io) {
            noteRepository.deleteNotesById(ids)
        }
    }

    val isUserAuth: Boolean
        get() = noteRepository.isUserAuth
}