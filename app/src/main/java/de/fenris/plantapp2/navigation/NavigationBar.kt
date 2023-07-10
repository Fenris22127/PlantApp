package de.fenris.plantapp2.navigation

import de.fenris.plantapp2.R

sealed class NavigationBar(var titleId: Int, var icon: Int, var route: String) {
    object Home : NavigationBar(R.string.home, R.drawable.ic_home, "home")
    object List : NavigationBar(R.string.list, R.drawable.ic_sprout, "list")
    object Task : NavigationBar(R.string.tasks, R.drawable.ic_task, "tasks")
    object Calendar : NavigationBar(R.string.calendar, R.drawable.ic_calendar, "calendar")
}