package com.stslex.feature_note_list.ui.top_bar

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_ui.R
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_note_list.ui.components.ExpandButton
import com.stslex.feature_note_list.ui.core.UIObjectsExt.clearSelection


@Composable
fun NotesShareIconButton(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
) {
    ExpandButton(
        modifier = modifier,
        imageVector = Icons.Outlined.Share,
        textResource = R.string.lb_share
    ) {
        context.sendNotes(selectedNotes)
        selectedNotes.clearSelection()
    }
}

private fun Context.sendNotes(notes: List<NoteDynamicUI>) {
    val sendingData = notes.joinToString(separator = "\n") {
        "${it.title}: \n${it.content}"
    }
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, sendingData)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, null)
    startActivity(shareIntent)
}

@Preview
@Composable
fun NotesShareIconButtonPreview() {
    AppTheme {
        NotesShareIconButton(selectedNotes = mutableStateListOf())
    }
}