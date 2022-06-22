package com.stslex.feature_profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.ProfileDestination
import com.stslex.feature_profile.ui.ProfileRoute

@OptIn(ExperimentalAnimationApi::class)
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