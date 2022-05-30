package com.example.feature_auth_phonenumber.navigation

import com.stslex.core_navigation.AppNavigationDestination

object PhoneNumberDestination : AppNavigationDestination {
    override val route: String
        get() = "phone_number_route"
    override val destination: String
        get() = "phone_number_destination"
}