package com.sebastiancorradi.myfriend.navigation

enum class Screen {
    MASTER,
    DETAILS,
}
sealed class NavigationItem(val route: String) {
    object Master : NavigationItem(Screen.MASTER.name)
    object Details : NavigationItem(Screen.DETAILS.name + "/{cat}")

}