package com.baharlou.buddies.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baharlou.buddies.domain.user.User
import com.baharlou.buddies.domain.validation.CredentialValidationResult
import com.baharlou.buddies.domain.validation.RegexValidator
import com.baharlou.buddies.signup.state.SignUpState

class SignUpViewModel(
    private val regexValidator: RegexValidator,
) {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(email: String, password: String) {

        when (regexValidator.validate(email, password)) {
            is CredentialValidationResult.InvalidEmail ->
                _mutableSignUpState.value = SignUpState.BadEmail

            is CredentialValidationResult.InvalidPassword ->
                _mutableSignUpState.value = SignUpState.BadPassword

            is CredentialValidationResult.Valid -> {
                val user = User("id", "mona@gm.com")
                _mutableSignUpState.value = SignUpState.SignedUp(user)
            }
        }

    }

}


