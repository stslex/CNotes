package com.example.feature_note_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.feature_note_list.data.NoteRepository
import com.stslex.core.Mapper
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val noteMapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>
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