package com.example.buildone.data

sealed class UIEvent {

    data class NameChanged(val name:String) : UIEvent()
    data class UserNameChanged(val userName:String) : UIEvent()
    data class EmailChanged(val email:String) : UIEvent()
    data class PasswordChanged(val password:String) : UIEvent()
}