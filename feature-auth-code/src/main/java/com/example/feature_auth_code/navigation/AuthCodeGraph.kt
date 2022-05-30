package com.example.feature_auth_code.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.feature_auth_code.ui.AuthCodeRoute
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authCodeGraph(
    popBackStack: () -> Unit,
    openProfile: () -> Unit
) {
    composable(
        route = "${AuthCodeDestination.route}/{${AuthCodeDestination.verificationIdArg}}",
        arguments = listOf(
            navArgument(AuthCodeDestination.verificationIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        val verificationId: String =
            it.arguments?.getString(AuthCodeDestination.verificationIdArg) ?: ""
        AuthCodeRoute(
            popBackStack = popBackStack,
            openProfile = openProfile,
            viewModel = getViewModel(parameters = { parametersOf(verificationId) })
        )
    }
}