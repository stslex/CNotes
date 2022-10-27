package com.stslex.feature_note_list.ui.top_bar

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_ui.R
import com.stslex.feature_note_list.ui.NotesViewModel
import com.stslex.feature_note_list.ui.core.UIObjectsExt.clearSelection

@Composable
fun NoteMediumTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    context: Context = LocalContext.current,
    viewModel: NotesViewModel
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
                visible = viewModel.selectedNotes.isEmpty(),
            ) {
                Row() {
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
                                    onClick = if (viewModel.isUserAuth) {
                                        viewModel.openProfile
                                    } else {
                                        viewModel.openAuthPhoneNumber
                                    }
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
        actions = noteListActions(selectedNotes = viewModel.selectedNotes),
        scrollBehavior = scrollBehavior
    )
}

private fun noteListActions(
    selectedNotes: SnapshotStateList<NoteDynamicUI>
): @Composable RowScope.() -> Unit = {
    AnimatedVisibility(visible = selectedNotes.isNotEmpty()) {
        ActionShare(selectedNotes)
    }
}

@Composable
private fun ActionShare(
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    IconButton(modifier = modifier, onClick = {
        val sendingData = selectedNotes.joinToString(separator = "\n") {
            "${it.title}: \n${it.content}"
        }
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, sendingData)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
        selectedNotes.clearSelection()
    }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = context.getString(R.string.lb_share)
        )
    }
}