package com.example.feature_note_list.ui.fab

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_ui.components.fabs.ExtendableFloatingActionButton

@Composable
fun NotesFab(
    lazyListState: LazyListState,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    deleteNotesFunction: (List<Int>) -> Unit,
    openSingleNote: (Int) -> Unit,
    isButtonVisible: MutableState<Boolean>
) {
    ExtendableFloatingActionButton(
        extended = !lazyListState.isScrollInProgress,
        text = animatedButtonContent(
            snapshotStateList = selectedNotes,
            targetContent = fabTestField(NotesFabResources.Delete),
            content = fabTestField(NotesFabResources.Create)
        ),
        icon = animatedButtonContent(
            snapshotStateList = selectedNotes,
            targetContent = fabIcon(NotesFabResources.Delete),
            content = fabIcon(NotesFabResources.Create)
        ),
        onClick = fabClickListener(
            selectedNotes = selectedNotes,
            deleteNotesFunction = deleteNotesFunction,
            openSingleNote = openSingleNote,
            isButtonVisible = isButtonVisible
        )
    )
}

private fun fabClickListener(
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    deleteNotesFunction: (List<Int>) -> Unit,
    openSingleNote: (Int) -> Unit,
    isButtonVisible: MutableState<Boolean>
): () -> Unit = {
    selectedNotes.apply {
        if (isNotEmpty()) {
            deleteNotesFunction(map { it.id })
            clearSelection()
        } else {
            isButtonVisible.value = false
            openSingleNote(-1)
        }
    }
}

private fun fabTestField(resources: NotesFabResources): @Composable () -> Unit = {
    Text(
        text = LocalContext.current.getString(resources.label)
    )
}

private fun fabIcon(resources: NotesFabResources): @Composable () -> Unit = {
    Icon(
        imageVector = resources.icon,
        contentDescription = LocalContext.current.getString(resources.label)
    )
}

fun SnapshotStateList<NoteDynamicUI>.clearSelection() {
    forEach { it.setSelect(false) }
    clear()
}