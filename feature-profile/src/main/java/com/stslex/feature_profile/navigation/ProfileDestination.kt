package com.stslex.feature_profile.navigation

import com.stslex.core_navigation.AppNavigationDestination

object ProfileDestination : AppNavigationDestination {
    override val route: String
        get() = "profile_route"
    override val destination: String
        get() = "profile_destination"
}