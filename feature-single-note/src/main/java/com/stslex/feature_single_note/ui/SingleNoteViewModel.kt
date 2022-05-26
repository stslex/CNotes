package com.stslex.feature_single_note.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import com.stslex.feature_single_note.data.NoteRepository
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository,
    private val mapper: Mapper.ToUI<NoteEntity, ValueState<NoteUI>>,
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[SingleNoteDestination.noteIdArg])

    val note: StateFlow<ValueState<NoteUI>> = repository.getNote(noteId)
        .mapLatest { it.map(mapper) }
        .flowOn(Dispatchers.IO)
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