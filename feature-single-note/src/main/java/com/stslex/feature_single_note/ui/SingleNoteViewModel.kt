package com.stslex.feature_single_note.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.ValueState
import com.stslex.core_model.ui.MapperNoteUIData
import com.stslex.core_model.ui.MapperNoteValueDataUI
import com.stslex.core_model.ui.NoteUIModel
import com.stslex.feature_single_note.data.NoteRepository
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleNoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository,
    private val mapper: MapperNoteValueDataUI,
    private val mapperNoteUIData: MapperNoteUIData
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[SingleNoteDestination.noteIdArg])

    val note: StateFlow<ValueState<NoteUIModel>> = repository.getNote(noteId)
        .mapLatest { it.map(mapper) }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    fun updateNote(note: NoteUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(mapperNoteUIData.map(note))
        }
    }
}