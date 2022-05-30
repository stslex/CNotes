package com.example.feature_auth_code.navigation

import com.stslex.core_navigation.AppNavigationDestination

object AuthCodeDestination : AppNavigationDestination {
    override val route: String
        get() = "auth_code_route"
    override val destination: String
        get() = "auth_code_destination"
    const val verificationIdArg: String = "verificationId"
}