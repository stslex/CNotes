package com.stslex.feature_note_list.ui.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.feature_note_list.ui.utils.ColorExt.surfaceColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotePagingItem(
    note: NoteDynamicUI,
    onNoteClick: () -> Unit,
    onNoteLongClick: () -> Unit
) {
    val color = note.surfaceColor()
    OutlinedCard(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    onNoteClick()
                },
                onLongClick = {
                    onNoteLongClick()
                }
            )
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = color.value,
            containerColor = color.value,
            disabledContainerColor = color.value
        ),
        content = {
            NoteItemContent(
                modifier = Modifier,
                note = note
            )
        }
    )
}