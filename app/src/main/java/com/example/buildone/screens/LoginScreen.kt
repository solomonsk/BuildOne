package com.example.buildone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buildone.components.ButtonComponent
import com.example.buildone.components.ClickableTextLoginComponent
import com.example.buildone.components.DividerTextComponent
import com.example.buildone.components.NormalTextComponent
import com.example.buildone.components.PasswordTextInputs
import com.example.buildone.components.TextInputs
import com.example.buildone.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
    ) {
        Column {
            Spacer(Modifier.height(20.dp))
            NormalTextComponent("Hey there", 24, fontWeight = FontWeight.Normal)
            Spacer(Modifier.height(20.dp))
            NormalTextComponent("Welcome back!!!", 30, FontWeight.Bold)
            Spacer(Modifier.height(130.dp))
            TextInputs("E-mail or username", Icons.Filled.Email)
            Spacer(Modifier.height(15.dp))
            PasswordTextInputs("Password", Icons.Filled.Lock)
            Spacer(Modifier.height(15.dp))
            ClickableTextLoginComponent(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate("forgot_my_password")
                },
                "Forgot my password",
                "Forgot my password",
            )
            Spacer(Modifier.height(30.dp))
            ButtonComponent("Login") {
                navController.navigate(Screen.LoginScreen.route)
            }
            Spacer(Modifier.height(60.dp))
            DividerTextComponent()
            Spacer(Modifier.height(140.dp))
            NormalTextComponent("Don't have an account yet?", 20, FontWeight.Normal)
            ClickableTextLoginComponent(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(Screen.SignUpScreen.route)
                },
                value = "Create account",
                linkText = "Create account"
            )
        }
    }
}