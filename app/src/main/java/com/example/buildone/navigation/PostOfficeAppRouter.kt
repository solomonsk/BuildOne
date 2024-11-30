package com.example.buildone.navigation

import androidx.navigation.NavController

sealed class Screen(val route: String) {
    data object SignUpScreen : Screen("sign_up_screen")
    data object LoginScreen : Screen("login_screen")
    data object ForgotPasswordScreen : Screen("forgot_pass_screen")
}


object PostOfficeAppRouter {

    fun navigateTo(navController: NavController, destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }
}