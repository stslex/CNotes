package com.stslex.feature_single_note.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.stslex.feature_single_note.ui.SingleNoteRoute
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.singleNoteGraph(popBackStack: () -> Unit) {
    composable(
        route = "${SingleNoteDestination.route}/{${SingleNoteDestination.noteIdArg}}",
        arguments = listOf(
            navArgument(SingleNoteDestination.noteIdArg) {
                type = NavType.IntType
            }
        ),
        deepLinks = listOf(
            navDeepLink {
                uriPattern =
                    "app://${SingleNoteDestination.route}/{${SingleNoteDestination.noteIdArg}}"
            })
    ) {
        val noteId: Int = it.arguments?.getInt(SingleNoteDestination.noteIdArg) ?: -1
        SingleNoteRoute(
            viewModel = getViewModel(parameters = { parametersOf(noteId) }),
            popBackStack = popBackStack
        )
    }
}