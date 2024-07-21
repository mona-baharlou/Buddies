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

            is CredentialValidationResult.Valid ->
                _mutableSignUpState.value = signUp(email, password)

        }

    }

    private fun signUp(
        email: String,
        password: String,
    ): SignUpState {
        val result = try {
            val user = createUser(email, password)
            SignUpState.SignedUp(user)
        } catch (duplicateAccount: DuplicateAccountExeption) {
            SignUpState.DuplicateAccount
        }
        return result
    }

    private fun createUser(
        email: String,
        password: String,
    ): User {

        checkAccountExists(email)
        val userId = createIdForAccounts(email)
        val user = User(userId, email)
        saveUser(password, user)
        return user
    }

    private fun saveUser(password: String, user: User) {
        usersAndPasswords.getOrPut(password, ::mutableListOf).add(user)
    }

    private fun createIdForAccounts(email: String): String {
        val userId = email.takeWhile { it != '@' } + "Id"
        return userId
    }

    private fun checkAccountExists(email: String) {
        if (usersAndPasswords.values
                .flatten()
                .any { it.username == email } //if Already have a user with the given email
        ) {
            throw DuplicateAccountExeption()
        }
    }

    class DuplicateAccountExeption : Throwable()

}


