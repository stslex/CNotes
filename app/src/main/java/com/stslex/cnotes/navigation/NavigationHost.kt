package com.stslex.cnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feature_auth_code.navigation.AuthCodeDestination
import com.example.feature_auth_code.navigation.authCodeGraph
import com.example.feature_auth_phonenumber.navigation.PhoneNumberDestination
import com.example.feature_auth_phonenumber.navigation.authPhoneNumberGraph
import com.example.feature_note_list.navigation.NoteListDestination
import com.example.feature_note_list.navigation.noteListGraph
import com.stslex.feature_profile.navigation.ProfileDestination
import com.stslex.feature_profile.navigation.profileGraph
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import com.stslex.feature_single_note.navigation.singleNoteGraph
import com.stslex.feature_todo.navigation.todoGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NoteListDestination.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        noteListGraph(
            openSingleNote = { navController.navigate("${SingleNoteDestination.route}/$it") },
            openProfile = { navController.navigate(ProfileDestination.route) }
        ) {
            singleNoteGraph(popBackStack = { navController.popBackStack() })
        }
        todoGraph()
        profileGraph(
            openAuthPhoneNumber = { navController.navigate(PhoneNumberDestination.route) },
            relaunchProfile = {
                navController.popBackStack()
                navController.navigate(ProfileDestination.route)
            }
        ) {
            authPhoneNumberGraph(
                popBackStack = { navController.navigate(NoteListDestination.route) },
                openCodeInput = { navController.navigate("${AuthCodeDestination.route}/$it") },
                openUserProfile = { navController.navigate(ProfileDestination.route) }
            )
            authCodeGraph(
                popBackStack = { navController.popBackStack() },
                openProfile = { navController.navigate(ProfileDestination.route) }
            )
        }
    }
}