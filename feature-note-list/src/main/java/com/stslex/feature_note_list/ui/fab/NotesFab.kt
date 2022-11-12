package com.stslex.feature_note_list.ui.fab

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex.core_ui.components.fabs.ExtendableFloatingActionButton
import com.stslex.feature_note_list.ui.NotesViewModel

@Composable
fun NotesFab(
    lazyListState: LazyListState,
    viewModel: NotesViewModel
) {
    ExtendableFloatingActionButton(
        extended = !lazyListState.isScrollInProgress,
        text = animatedButtonContent(
            snapshotStateList = viewModel.selectedNotes,
            targetContent = fabTestField(NotesFabResources.Delete),
            content = fabTestField(NotesFabResources.Create)
        ),
        icon = animatedButtonContent(
            snapshotStateList = viewModel.selectedNotes,
            targetContent = fabIcon(NotesFabResources.Delete),
            content = fabIcon(NotesFabResources.Create)
        ),
        onClick = viewModel::onCreateButtonClicked
    )
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