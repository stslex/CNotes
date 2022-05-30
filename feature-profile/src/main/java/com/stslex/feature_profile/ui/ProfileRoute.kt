package com.stslex.feature_profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_profile.navigation.ProfileDestination
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    relaunchProfile: () -> Unit,
    viewModel: ProfileViewModel = getViewModel()
) {
    ProfileScreenContent(
        modifier = modifier,
        relaunchProfile = relaunchProfile,
        signOut = viewModel::signOut
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    relaunchProfile: () -> Unit,
    signOut: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    text = "Synced Notes: 5"
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    text = "Need to sync: 3"
                )

                TextButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    onClick = { /*TODO*/ }) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Sync"
                    )
                }
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            onClick = {
                signOut()
                relaunchProfile()
            }) {
            Text(text = "sign out")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenContentPreview() {
    AppTheme {
        ProfileScreenContent(Modifier, {}, {})
    }
}