package com.example.buildone.data.rules

object Validator {

    fun validateName(name: String): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty() && name.length >= 6)
        )
    }

    fun validateUserName(userName: String): ValidationResult {
        return ValidationResult(
            (userName.isNotEmpty() && userName.length >= 4)
        )
    }
    fun validateEMail(eMail: String): ValidationResult {
        return ValidationResult(
            (eMail.isNotEmpty() && eMail.length >= 6)
        )
    }

    fun validatePassword(passWord: String): ValidationResult {
        return ValidationResult(
            (passWord.isNotEmpty() && passWord.length >= 6)
        )
    }
    fun validatePolicy(policy: Boolean): ValidationResult {
        return ValidationResult(!policy)
    }
}

data class ValidationResult(
    val status: Boolean = false
)