package st.slex.feature_main.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.stslex.core_navigation.TopLevelDestination
import st.slex.feature_main.utils.MainScreenExt.isSelected
import st.slex.feature_main.utils.MainScreenExt.topLevelDestinationList

@Composable
fun AppNavRail(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        topLevelDestinationList.forEach { destination ->
            val selected = currentDestination.isSelected(destination)
            NavigationRailCustomItem(
                destination = destination,
                selected = selected
            ) {
                onNavigateToTopLevelDestination(destination)
            }
        }
    }
}

@Composable
private fun NavigationRailCustomItem(
    destination: TopLevelDestination,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = navigationRailIcon(destination, selected),
        label = navigationRailLabel(destination),
        alwaysShowLabel = false
    )
}

private fun navigationRailIcon(
    destination: TopLevelDestination,
    selected: Boolean
): @Composable () -> Unit = {
    Icon(
        destination.icon(selected),
        contentDescription = null
    )
}

private fun navigationRailLabel(
    destination: TopLevelDestination
): @Composable () -> Unit = {
    Text(stringResource(destination.iconTextId))
}