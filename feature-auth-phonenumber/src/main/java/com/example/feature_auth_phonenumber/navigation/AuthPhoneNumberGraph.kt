package com.example.feature_auth_phonenumber.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature_auth_phonenumber.ui.PhoneNumberRoute

fun NavGraphBuilder.authPhoneNumberGraph(
    popBackStack: () -> Unit,
    openCodeInput: (String) -> Unit,
    openUserProfile: () -> Unit
) {
    composable(
        route = PhoneNumberDestination.route,
    ) {
        PhoneNumberRoute(
            popBackStack = popBackStack,
            openCodeInput = openCodeInput,
            openUserProfile = openUserProfile
        )
    }
}