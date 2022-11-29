package com.stslex.cnotes.shortcut

import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.destinations.SingleNoteDestination
import com.stslex.core_ui.R
import com.stslex.feature_main.R.drawable


sealed class AppShortcuts(
    val shortcut: Shortcut
) {

    object Profile : AppShortcuts(
        Shortcut(
            labelSource = R.string.lb_short_shortcut_profile,
            shortLabelSource = R.string.lb_short_shortcut_profile,
            longLabelSource = R.string.lb_long_shortcut_profile,
            disabledMessageSource = R.string.lb_shortcut_disabled_message,
            iconSource = drawable.ic_baseline_person_outline_24,
            intentDestination = ProfileDestination.route
        )
    )

    object CreateNewNote : AppShortcuts(
        Shortcut(
            labelSource = R.string.lb_short_shortcut_create,
            shortLabelSource = R.string.lb_short_shortcut_create,
            longLabelSource = R.string.lb_long_shortcut_create,
            disabledMessageSource = R.string.lb_shortcut_disabled_message,
            iconSource = drawable.ic_baseline_add_24,
            intentDestination = "${SingleNoteDestination.route}/-1"
        )
    )
}
