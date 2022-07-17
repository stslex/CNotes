package com.stslex.feature_auth_phonenumber.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.stslex.feature_auth_phonenumber.ui.PhoneNumberRoute
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.destinations.PhoneNumberDestination

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authPhoneNumberGraph(
    popBackStack: () -> Unit,
    openCodeInput: (String) -> Unit,
    openUserProfile: () -> Unit
) {
    composable(
        route = PhoneNumberDestination.route,
        enterTransition = AuthPhoneTransitions.enterTransition,
        exitTransition = AuthPhoneTransitions.exitTransition
    ) {
        PhoneNumberRoute(
            popBackStack = popBackStack,
            openCodeInput = openCodeInput,
            openUserProfile = openUserProfile
        )
    }
}