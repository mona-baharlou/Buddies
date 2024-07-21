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

    val usersAndPasswords = mutableMapOf<String, MutableList<User>>()

    fun createAccount(email: String, password: String) {

        when (regexValidator.validate(email, password)) {
            is CredentialValidationResult.InvalidEmail ->
                _mutableSignUpState.value = SignUpState.BadEmail

            is CredentialValidationResult.InvalidPassword ->
                _mutableSignUpState.value = SignUpState.BadPassword

            is CredentialValidationResult.Valid -> {

                if (email.contains("neda")) {
                    _mutableSignUpState.value = SignUpState.DuplicateAccount
                } else {
                    val exists = usersAndPasswords.values
                        .flatten()
                        .any { it.username == email } //if Already have a user with the given email

                    if (exists/*email.contains("sara")*/) {
                        val user = User("saraId", email)
                        _mutableSignUpState.value = SignUpState.SignedUp(user)
                    } else {
                        val user = createUser(email, password)
                        _mutableSignUpState.value = SignUpState.SignedUp(user)
                    }
                }
            }
        }

    }

    private fun createUser(
        email: String,
        password: String,
    ): User {
        val userId = email.takeWhile { it != '@' } + "Id"
        val user = User(userId, email)
        usersAndPasswords.getOrPut(password, ::mutableListOf).add(user)
        return user
    }

}


