package com.example.jetpackcomposeapp.ui

sealed class ScreenNavigation(val route: String){
    object Home: ScreenNavigation("home")
    object Detail: ScreenNavigation("home/{originoteId}"){
        fun createRoute(originoteId: Long) = "home/$originoteId"
    }
    object Profile: ScreenNavigation("profile")
}