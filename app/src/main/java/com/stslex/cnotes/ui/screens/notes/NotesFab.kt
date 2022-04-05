package com.stslex.cnotes.ui.screens.notes

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import com.stslex.cnotes.ui.common_elements.ExtendableFloatingActionButton
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.cnotes.ui.navigation.NavScreen

@ExperimentalAnimationApi
fun notesFab(
    lazyListState: LazyListState,
    selectedNotes: SnapshotStateList<NoteUIModel>,
    deleteNotesFunction: (List<Int>) -> Unit,
    navController: NavController
): @Composable () -> Unit = {
    ExtendableFloatingActionButton(
        extended = !lazyListState.isScrollInProgress,
        text = animatedButtonContent(
            snapshotStateList = selectedNotes,
            targetContent = textDelete(),
            content = textCreate()
        ),
        icon = animatedButtonContent(
            snapshotStateList = selectedNotes,
            targetContent = iconDelete(),
            content = iconCreate()
        ),
        onClick = {
            if (selectedNotes.isNotEmpty()) {
                deleteNotesFunction.invoke(selectedNotes.map(transformIds))
                selectedNotes.clearAllItems()
            } else {
                navController.navigate(NavScreen.EditNoteScreen.route)
            }
        }
    )
}

private fun SnapshotStateList<NoteUIModel>.clearAllItems() {
    forEach { it.setSelect(false) }
    clear()
}

private val transformIds: (NoteUIModel) -> Int
    get() = { note ->
        note.id()
    }

private fun textDelete(): @Composable () -> Unit = {
    Text(text = "delete")
}

private fun textCreate(): @Composable () -> Unit = {
    Text(text = "create")
}

private fun iconDelete(): @Composable () -> Unit = {
    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
}

private fun iconCreate(): @Composable () -> Unit = {
    Icon(imageVector = Icons.Default.Create, contentDescription = "create")
}

@ExperimentalAnimationApi
private inline fun animatedButtonContent(
    snapshotStateList: SnapshotStateList<NoteUIModel>,
    crossinline targetContent: @Composable () -> Unit,
    crossinline content: @Composable () -> Unit
): @Composable () -> Unit = {
    AnimatedContent(
        targetState = snapshotStateList.isNotEmpty(),
        transitionSpec = transitionContentButtonSpec()
    ) { targetState ->
        if (targetState) targetContent() else content()
    }
}

@ExperimentalAnimationApi
private fun transitionContentButtonSpec(): AnimatedContentScope<Boolean>.() -> ContentTransform = {
    fadeIn(animationSpec = tween(500, 150)) with
            fadeOut(animationSpec = tween(500)) using
            SizeTransform { initialSize, targetSize ->
                if (targetState) {
                    keyframes {
                        IntSize(targetSize.width, initialSize.height) at 150
                        durationMillis = 300
                    }
                } else {
                    keyframes {
                        IntSize(initialSize.width, targetSize.height) at 150
                        durationMillis = 300
                    }
                }
            }
}