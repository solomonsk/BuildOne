package com.example.buildone.data

data class RegistrationUIState (
    var name : String = "",
    var userName : String = "",
    var email : String = "",
    var password : String = "",
    var policyChecked   : Boolean = false,

    val nameError : Boolean = false,
    val userNameError : Boolean = false,
    val eMailError : Boolean = false,
    val passWordError : Boolean = false,
    val policyError : Boolean = false,

)