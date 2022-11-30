package com.stslex.feature_note_list.ui.fab

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_ui.components.fabs.ExtendableFloatingActionButton

@Composable
fun NotesFab(
    lazyListState: LazyListState,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    onCreateButtonClicked: () -> Unit,
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
        onClick = onCreateButtonClicked
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