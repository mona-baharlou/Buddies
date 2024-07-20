package com.baharlou.buddies.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baharlou.buddies.domain.validation.CredentialValidationResult
import com.baharlou.buddies.domain.validation.RegexValidator
import com.baharlou.buddies.signup.state.SignUpState

class SignUpViewModel {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(email: String, password: String) {

        val state = when (RegexValidator().validate(email, password)) {
            is CredentialValidationResult.InvalidEmail ->
                SignUpState.BadEmail

            is CredentialValidationResult.InvalidPassword ->
                SignUpState.BadPassword
        }

        _mutableSignUpState.value = state
    }

}


