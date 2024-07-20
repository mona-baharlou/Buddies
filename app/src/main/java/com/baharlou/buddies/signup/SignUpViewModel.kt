package com.baharlou.buddies.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baharlou.buddies.domain.validation.CredentialValidationResult
import com.baharlou.buddies.domain.validation.RegexValidator
import com.baharlou.buddies.signup.state.SignUpState
import java.util.regex.Pattern

class SignUpViewModel(
    private val regexValidator: RegexValidator,
) {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(email: String, password: String) {

        val state = when (regexValidator.validate(email, password)) {
            is CredentialValidationResult.InvalidEmail ->
                SignUpState.BadEmail

            is CredentialValidationResult.InvalidPassword ->
                SignUpState.BadPassword
        }

        _mutableSignUpState.value = state
    }

    private fun validate(
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


