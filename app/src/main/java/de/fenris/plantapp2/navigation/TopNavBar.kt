package de.fenris.plantapp2.navigation

import de.fenris.plantapp2.R

sealed class TopNavBar(var titleId: Int, var route: String) {
    object Home : TopNavBar(R.string.home, "home")
    object List : TopNavBar(R.string.list, "list")
    object Task : TopNavBar(R.string.tasks, "tasks")
    object Calendar : TopNavBar(R.string.calendar, "calendar")
}