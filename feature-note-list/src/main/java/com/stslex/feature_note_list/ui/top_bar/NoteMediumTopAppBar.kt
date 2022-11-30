package com.stslex.feature_note_list.ui.top_bar

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteMediumTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    context: Context = LocalContext.current,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    onProfileButtonClicked: () -> Unit
) {
    var profileButtonClicked by remember { mutableStateOf(false) }
    MediumTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = context.getString(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            AnimatedVisibility(
                visible = selectedNotes.isEmpty(),
            ) {
                Row {
                    IconButton(onClick = { profileButtonClicked = !profileButtonClicked }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = context.getString(R.string.lb_account)
                        )
                    }
                    AnimatedVisibility(visible = profileButtonClicked) {
                        LazyRow {
                            item {
                                Spacer(modifier = Modifier.padding(4.dp))
                                Button(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = onProfileButtonClicked
                                ) {
                                    Text(text = context.getString(R.string.lb_short_shortcut_profile))
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.padding(4.dp))
                                Button(modifier = Modifier.padding(end = 4.dp), onClick = {}) {
                                    Text(text = "Settings")
                                }
                            }
                        }
                    }
                }
            }
        },
        actions = {
            AnimatedVisibility(visible = selectedNotes.isNotEmpty()) {
                NotesShareIconButton(selectedNotes = selectedNotes)
            }
        },
        scrollBehavior = scrollBehavior
    )
}