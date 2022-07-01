package com.stslex.core_navigation.destinations

object AuthCodeDestination : AppDestinations {
    override val route: String
        get() = "auth_code_route"
    override val destination: String
        get() = "auth_code_destination"
    const val verificationIdArg: String = "verificationId"
}