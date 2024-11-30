package com.example.buildone.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buildone.navigation.Screen
import com.example.buildone.screens.ForgotPasswordScreen
import com.example.buildone.screens.LoginScreen
import com.example.buildone.screens.SignUpScreen

@Composable
fun PostOfficeApp() {
    val navController = rememberNavController()
    Surface(
        Modifier.fillMaxSize(),
        color = Color.White
    ) {
        NavHost(navController = navController, startDestination = Screen.SignUpScreen.route) {
            composable(Screen.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.SignUpScreen.route) {
                SignUpScreen(navController = navController)
            }
            composable(Screen.ForgotPasswordScreen.route) {
                ForgotPasswordScreen(navController = navController)
            }
        }
    }
}