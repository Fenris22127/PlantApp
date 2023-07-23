package de.fenris.plantapp2.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import de.fenris.plantapp2.ui.theme.getNavColor

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationBar.Home,
        NavigationBar.List,
        NavigationBar.Task,
        NavigationBar.Calendar
    )
    NavigationBar(
        containerColor = getNavColor()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.titleId)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.titleId),
                        fontSize = 9.sp
                    )
                },
                alwaysShowLabel = true,
                selected = navController.currentBackStackEntryAsState().value?.destination?.route == item.route /*currentRoute == item.route*/,
                onClick = {
                    navController.navigate(item.route) {
                        //popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        /*navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true*/
                    }
                }
            )
        }
    }
}