package com.stslex.cnotes.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.stslex.core_navigation.destinations.AuthCodeDestination
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.core_navigation.destinations.PhoneNumberDestination
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.feature_auth_code.navigation.authCodeGraph
import com.stslex.feature_auth_phonenumber.navigation.authPhoneNumberGraph
import com.stslex.feature_note_list.navigation.noteListGraph
import com.stslex.feature_profile.navigation.profileGraph
import com.stslex.feature_single_note.navigation.singleNoteGraph
import com.stslex.feature_todo.navigation.todoGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NoteListDestination.route
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        noteListGraph {
            singleNoteGraph(popBackStack = { navController.popBackStack() })
        }
        todoGraph()
        profileGraph(
            openAuthPhoneNumber = {
                navController.navigate(PhoneNumberDestination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        authPhoneNumberGraph(
            popBackStack = { navController.popBackStack() },
            openCodeInput = { navController.navigate("${AuthCodeDestination.route}/$it") },
            openUserProfile = { navController.navigate(ProfileDestination.route) }
        )
        authCodeGraph(
            popBackStack = { navController.popBackStack() },
            openProfile = {
                navController.navigate(ProfileDestination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}