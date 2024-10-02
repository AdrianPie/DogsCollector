package org.example.dogcollector.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home_screen")
    data object DogList: Screen("dog_list_screen")

}
