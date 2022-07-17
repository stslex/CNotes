package com.stslex.feature_single_note.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import com.stslex.feature_single_note.data.abstraction.SingleNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SingleNoteViewModel(
    dispatchers: AppDispatchers,
    mapper: Mapper.ToUI<NoteEntity, ValueState<NoteUI>>,
    noteId: Int,
    private val repository: SingleNoteRepository,
) : ViewModel() {

    val note: StateFlow<ValueState<NoteUI>> = repository.getNote(noteId)
        .mapLatest { it.map(mapper) }
        .flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    fun updateNote(note: NoteUI) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }
}