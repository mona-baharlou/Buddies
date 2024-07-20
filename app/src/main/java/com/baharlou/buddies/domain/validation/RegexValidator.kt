package com.baharlou.buddies.domain.validation

import java.util.regex.Pattern

class RegexValidator {
    fun validate(
        email: String,
        password: String,
    ): CredentialValidationResult {
        val emailRegex =
            "[a-zA-Z0-9+._\\%-+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9-]{0,64}(.[a-zA-Z0-9][a-zA-Z0-9-]{0,25})+"
        val emailPattern = Pattern.compile(emailRegex)

        val passwordRegex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""
        val passwordPattern = Pattern.compile(passwordRegex)


        val result = if (!emailPattern.matcher(email).matches()) {
            CredentialValidationResult.InvalidEmail
        } else if (!passwordPattern.matcher(password).matches()) {
            CredentialValidationResult.InvalidPassword

        } else TODO()
        return result
    }
}