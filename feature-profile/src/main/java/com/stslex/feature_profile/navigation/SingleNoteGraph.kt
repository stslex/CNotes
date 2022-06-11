package com.stslex.feature_profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.stslex.feature_profile.ui.ProfileRoute

fun NavGraphBuilder.profileGraph(
    openAuthPhoneNumber: () -> Unit
) {
    composable(
        route = ProfileDestination.route,
        deepLinks = listOf(navDeepLink { uriPattern = "app://${ProfileDestination.route}" })
    ) {
        ProfileRoute(openAuthPhoneNumber = openAuthPhoneNumber)
    }
}