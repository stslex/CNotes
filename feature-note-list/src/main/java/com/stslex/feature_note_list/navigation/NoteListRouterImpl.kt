package com.stslex.feature_note_list.navigation

import androidx.navigation.NavController
import com.stslex.core_navigation.destinations.PhoneNumberDestination
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.destinations.SingleNoteDestination

class NoteListRouterImpl(
    private val navController: NavController
) : NoteListRouter {

    override val openSingleNote: (Int) -> Unit
        get() = { noteId ->
            navController.navigate("${SingleNoteDestination.route}/$noteId")
        }

    override val openProfile: () -> Unit
        get() = { navController.navigate(ProfileDestination.route) }

    override val openAuthPhoneNumber: () -> Unit
        get() = { navController.navigate(PhoneNumberDestination.route) }
}