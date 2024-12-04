package com.example.buildone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buildone.components.ButtonComponent
import com.example.buildone.components.CheckBox
import com.example.buildone.components.ClickableTextComponent
import com.example.buildone.components.ClickableTextLoginComponent
import com.example.buildone.components.DividerTextComponent
import com.example.buildone.components.NormalTextComponent
import com.example.buildone.components.PasswordTextInputs
import com.example.buildone.components.TextInputs
import com.example.buildone.data.LoginViewModel
import com.example.buildone.data.UIEvent
import com.example.buildone.data.rules.ValidationResult
import com.example.buildone.data.rules.Validator
import com.example.buildone.navigation.Screen

@Composable
fun SignUpScreen(navController: NavController,loginViewModel: LoginViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
    ) {
        Column {
            Spacer(Modifier.height(20.dp))
            NormalTextComponent("Hello there!!", 24, FontWeight.Normal)
            Spacer(Modifier.height(20.dp))
            NormalTextComponent("Create an account", 30, FontWeight.Bold)
            Spacer(Modifier.height(20.dp))
            TextInputs("Name", Icons.Outlined.Person, onTextSelected = {
                loginViewModel.onEvent(UIEvent.NameChanged(it))
            }, errorStatus = loginViewModel.registrationUIState.value.nameError)
            Spacer(Modifier.height(15.dp))
            TextInputs("Username", Icons.Filled.Person, onTextSelected = {
                loginViewModel.onEvent(UIEvent.UserNameChanged(it))
            }, errorStatus = loginViewModel.registrationUIState.value.userNameError)
            Spacer(Modifier.height(15.dp))
            TextInputs("E-mail ID", Icons.Filled.Email, onTextSelected = {
                loginViewModel.onEvent(UIEvent.EmailChanged(it))
            }, errorStatus = loginViewModel.registrationUIState.value.eMailError)
            Spacer(Modifier.height(15.dp))
            PasswordTextInputs("Password", Icons.Filled.Lock, onTextSelected = {
                loginViewModel.onEvent(UIEvent.PasswordChanged(it))
            }, errorStatus = loginViewModel.registrationUIState.value.passWordError)
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckBox(onCheckedChange = {
                    loginViewModel.onEvent(UIEvent.PolicyChecked(it))
                })
                Spacer(modifier = Modifier.width(8.dp))
                ClickableTextComponent(
                    value = "By continuing you accept our Private policy and Terms of use",
                    linkText = "Private policy",
                    hyperlinks = "https://google.com"
                )
            }
            Spacer(Modifier.height(15.dp))
            ButtonComponent("Register", onButtonClicked = {
                loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
            }, isEnabled = loginViewModel.validationPassed.value) {
                navController.navigate(Screen.LoginScreen.route)
            }
            Spacer(Modifier.height(50.dp))
            DividerTextComponent()
            Spacer(Modifier.height(90.dp))
            NormalTextComponent("Already have an account?", 20, FontWeight.Normal)
            ClickableTextLoginComponent(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(Screen.LoginScreen.route)
                },
                value = "Login",
                linkText = "Login"
            )
        }
    }
}


//@Preview
//@Composable
//fun Preview() {
//    SignUpScreen()
//}