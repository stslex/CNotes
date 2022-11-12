package st.slex.feature_main.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.feature_note_list.navigation.noteListTopLevelDestination
import com.stslex.feature_todo.navigation.todoTopLevelDestination

object MainScreenExt {

    @JvmStatic
    val topLevelDestinationList = listOf(noteListTopLevelDestination, todoTopLevelDestination)

    val WindowSizeClass.isCompact: Boolean
        get() = widthSizeClass == WindowWidthSizeClass.Compact

    val NavDestination?.isTopLevel: Boolean
        get() = topLevelDestinationList.map { it.destination }.contains(this?.route.orEmpty())

    val NavDestination?.isSelected: (destination: TopLevelDestination) -> Boolean
        get() = { destination ->
            this?.hierarchy?.any { hierarchyDestination ->
                hierarchyDestination.route == destination.route
            } ?: false
        }

    @JvmStatic
    val appbarInsets: @Composable () -> WindowInsets = {
        WindowInsets.safeDrawing.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
        )
    }
}