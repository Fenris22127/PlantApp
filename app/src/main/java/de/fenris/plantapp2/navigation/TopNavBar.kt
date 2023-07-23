package de.fenris.plantapp2.navigation

sealed class TopNavBar(var route: String) {
    object Home : TopNavBar("home")
    object List : TopNavBar("list")
    object Task : TopNavBar("tasks")
    object Calendar : TopNavBar("calendar")
}