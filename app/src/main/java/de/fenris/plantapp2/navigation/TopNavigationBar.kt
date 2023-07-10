package de.fenris.plantapp2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import de.fenris.plantapp2.R

@Composable
fun TopNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    TopBar(
        title = when (currentRoute) {
            "home" -> stringResource(id = NavigationBar.Home.titleId)
            "list" -> stringResource(id = NavigationBar.List.titleId)
            "tasks" -> stringResource(id = NavigationBar.Task.titleId)
            "calendar" -> stringResource(id = NavigationBar.Calendar.titleId)
            else -> stringResource(id = R.string.app_name)
        }
    )
}