package st.slex.feature_main.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.core_ui.components.ClearRippleTheme
import st.slex.feature_main.utils.MainScreenExt.appbarInsets
import st.slex.feature_main.utils.MainScreenExt.isSelected
import st.slex.feature_main.utils.MainScreenExt.topLevelDestinationList

@Composable
fun AppBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                modifier = Modifier.windowInsetsPadding(appbarInsets()),
                tonalElevation = 0.dp
            ) {
                topLevelDestinationList.forEach { destination ->
                    AppBarBottomItem(
                        onNavigateToTopLevelDestination = onNavigateToTopLevelDestination,
                        currentDestination = currentDestination,
                        destination = destination
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.AppBarBottomItem(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    destination: TopLevelDestination,
) {
    val selected = currentDestination.isSelected(destination)
    NavigationBarItem(
        selected = selected,
        onClick = { onNavigateToTopLevelDestination(destination) },
        icon = {
            Icon(
                imageVector = destination.icon(selected),
                contentDescription = null
            )
        },
        label = { Text(stringResource(destination.iconTextId)) },
        alwaysShowLabel = false
    )
}