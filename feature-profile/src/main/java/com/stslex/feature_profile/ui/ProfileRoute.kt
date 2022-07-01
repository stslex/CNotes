package com.stslex.feature_profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core.ValueState
import com.stslex.core_resources.R.string
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_profile.ui.components.ButtonLabel
import com.stslex.feature_profile.ui.components.NotesSizeStateLabel
import com.stslex.feature_profile.ui.components.ProfileActionButtons
import org.koin.androidx.compose.get

@Composable
fun ProfileRoute(openAuthPhoneNumber: () -> Unit) {
    ProfileScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        openAuthPhoneNumber = openAuthPhoneNumber
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    openAuthPhoneNumber: () -> Unit,
    viewModel: ProfileViewModel = get()
) {
    ProfileScreenContent(
        modifier = modifier,
        openAuthPhoneNumber = openAuthPhoneNumber,
        signOut = viewModel::signOut,
        uploadNotes = viewModel::uploadNotes,
        downloadNotes = viewModel::downloadNotes,
        syncNoteSize = viewModel.syncNoteSize.collectAsState(initial = ValueState.Loading),
        localNotesSize = viewModel.localNotesSize.collectAsState(initial = ValueState.Loading),
        remoteNotesSize = viewModel.remoteNoteSize.collectAsState(initial = ValueState.Loading)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    openAuthPhoneNumber: () -> Unit,
    signOut: () -> Unit,
    downloadNotes: () -> Unit,
    uploadNotes: () -> Unit,
    syncNoteSize: State<ValueState<Int>>,
    localNotesSize: State<ValueState<Int>>,
    remoteNotesSize: State<ValueState<Int>>
) {
    Box(modifier = modifier.fillMaxSize()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
            ) {
                System.out.println("remoteNotesSize::" + remoteNotesSize.value)
                NotesSizeStateLabel(labelId = string.lb_local_notes, notesState = localNotesSize)
                NotesSizeStateLabel(labelId = string.lb_remote_notes, notesState = remoteNotesSize)
                NotesSizeStateLabel(labelId = string.lb_synced_notes, notesState = syncNoteSize)

                ProfileActionButtons(
                    actionDownload = downloadNotes,
                    actionUpload = uploadNotes
                )
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            onClick = {
                signOut()
                openAuthPhoneNumber()
            }
        ) {
            ButtonLabel(label = LocalContext.current.getString(string.b_sign_out))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenContentPreview() {
    AppTheme {

    }
}