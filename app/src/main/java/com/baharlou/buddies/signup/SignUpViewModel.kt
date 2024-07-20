package com.baharlou.buddies.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baharlou.buddies.signup.state.SignUpState

class SignUpViewModel {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(email: String, password: String) {

        _mutableSignUpState.value = SignUpState.BadEmail
    }

}
