package com.stslex.feature_profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.stslex.feature_profile.ui.ProfileRoute

fun NavGraphBuilder.profileGraph(
    openAuthPhoneNumber: () -> Unit,
    relaunchProfile: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = ProfileDestination.route,
        startDestination = ProfileDestination.destination
    ) {
        composable(route = ProfileDestination.destination) {
            ProfileRoute(
                openAuthPhoneNumber = openAuthPhoneNumber,
                relaunchProfile = relaunchProfile
            )
        }
        nestedGraphs()
    }
}