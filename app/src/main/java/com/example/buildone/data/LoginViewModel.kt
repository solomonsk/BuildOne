package com.example.buildone.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.buildone.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var validationPassed = mutableStateOf(false)

    fun onEvent(event: UIEvent) {
        validateDateWithRules()
        when (event) {
            is UIEvent.NameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
                printState()
            }

            is UIEvent.UserNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    userName = event.userName
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is UIEvent.PolicyChecked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    policyChecked= event.policy
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "SignUp")
        printState()
        createUsers(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDateWithRules() {
        val Nameresult = Validator.validateName(
            name = registrationUIState.value.name
        )
        val UserNameresult = Validator.validateUserName(
            userName = registrationUIState.value.userName
        )
        val Emailresult = Validator.validateEMail(
            eMail = registrationUIState.value.email
        )
        val Passwordresult = Validator.validatePassword(
            passWord = registrationUIState.value.password
        )
        val Policyresult = Validator.validatePolicy(
            policy = registrationUIState.value.policyChecked
        )

        Log.d(TAG, "SignUp")
        Log.d(TAG, "Firstname = $Nameresult")
        Log.d(TAG, "Username = $UserNameresult")
        Log.d(TAG, "Email = $Emailresult")
        Log.d(TAG, "Password = $Passwordresult")
        Log.d(TAG, "Checked = $Policyresult")

        registrationUIState.value = registrationUIState.value.copy(
            nameError = Nameresult.status,
            userNameError = UserNameresult.status,
            eMailError = Emailresult.status,
            passWordError = Passwordresult.status,
            policyError = Policyresult.status
        )

        validationPassed.value = Nameresult.status && UserNameresult.status
                && Emailresult.status && Passwordresult.status && Policyresult.status
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

    fun createUsers(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }
}