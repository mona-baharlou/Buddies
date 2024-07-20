package com.baharlou.buddies.signup

import com.baharlou.buddies.InstantTaskExecutorExtension
import com.baharlou.buddies.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CredentialValidationTest {

    @Test
    fun invalidEmail(){
        val viewModel = SignUpViewModel()
        viewModel.createAccount("mona", "password")
        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }
}