package com.example.feature_auth_phonenumber.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.example.feature_auth_phonenumber.ui.PhoneNumberRoute
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.AuthCodeDestination
import com.stslex.core_navigation.NoteListDestination
import com.stslex.core_navigation.PhoneNumberDestination
import com.stslex.core_navigation.ProfileDestination

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authPhoneNumberGraph(
    popBackStack: () -> Unit,
    openCodeInput: (String) -> Unit,
    openUserProfile: () -> Unit
) {
    composable(
        route = PhoneNumberDestination.route,
        enterTransition = {
            when (initialState.destination.route) {
                NoteListDestination.destination, ProfileDestination.route ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                AuthCodeDestination.route ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                NoteListDestination.destination, ProfileDestination.route ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                AuthCodeDestination.route -> slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
                else -> null
            }
        }
    ) {
        PhoneNumberRoute(
            popBackStack = popBackStack,
            openCodeInput = openCodeInput,
            openUserProfile = openUserProfile
        )
    }
}