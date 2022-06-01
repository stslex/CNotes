package com.stslex.feature_profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_profile.navigation.ProfileDestination
import org.koin.androidx.compose.get

@Composable
fun ProfileRoute(openAuthPhoneNumber: () -> Unit, relaunchProfile: () -> Unit) {
    if (FirebaseApp.getApps(LocalContext.current).isEmpty()) {
        FirebaseApp.initializeApp(LocalContext.current, get(), ProfileDestination.destination)
    }
    if (FirebaseAuth.getInstance().currentUser == null) {
        openAuthPhoneNumber()
    } else {
        ProfileScreen(
            modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
            relaunchProfile = relaunchProfile
        )
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    relaunchProfile: () -> Unit,
    viewModel: ProfileViewModel = get()
) {
    viewModel.getSyncNotesSize()
    viewModel.getRemoteNotesSize()
    ProfileScreenContent(
        modifier = modifier,
        relaunchProfile = relaunchProfile,
        signOut = viewModel::signOut,
        syncNotes = viewModel::synchronizeNotes,
        syncNoteSize = viewModel.syncNoteSize.collectAsState(initial = 0),
        localNotesSize = viewModel.localNotesSize.collectAsState(initial = 0),
        remoteNotesSize = viewModel.remoteNoteSize.collectAsState(initial = 0)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    relaunchProfile: () -> Unit,
    signOut: () -> Unit,
    syncNotes: () -> Unit,
    syncNoteSize: State<Int>,
    localNotesSize: State<Int>,
    remoteNotesSize: State<Int>
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
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Local Notes: ${localNotesSize.value}"
                )

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Remote Notes: ${remoteNotesSize.value}"
                )

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Synced Notes: ${syncNoteSize.value}"
                )

                ProfileActionButtons(
                    actionDownload = {},
                    actionUpload = syncNotes
                )
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            onClick = {
                signOut()
                relaunchProfile()
            }
        ) {
            ButtonLabel(label = "sign out")
        }
    }
}

@Composable
fun ProfileActionButtons(
    modifier: Modifier = Modifier,
    actionDownload: () -> Unit,
    actionUpload: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        ButtonProfileAction(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            label = "Download",
            imageVector = Icons.Filled.KeyboardArrowDown,
            onClick = actionDownload
        )

        ButtonProfileAction(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            label = "Upload",
            imageVector = Icons.Filled.KeyboardArrowUp,
            onClick = actionUpload
        )
    }
}

@Composable
fun ButtonProfileAction(
    modifier: Modifier = Modifier,
    label: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        ButtonLabel(
            modifier = Modifier.weight(4f),
            label = label
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = imageVector,
            contentDescription = label
        )
    }
}

@Composable
fun ButtonLabel(modifier: Modifier = Modifier, label: String) {
    Text(
        modifier = modifier,
        maxLines = 1,
        text = label,
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenContentPreview() {
    AppTheme {

    }
}