package com.baharlou.buddies.signup

import com.baharlou.buddies.InstantTaskExecutorExtension
import com.baharlou.buddies.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(InstantTaskExecutorExtension::class)
class CredentialValidationTest {

    @ParameterizedTest
    @CsvSource(
        "'email'",
        "'a@b.c'",
        "'ab@b.c'",
        "'ab@bc.c'",
        "''",
        "'   '",
    )
    fun invalidEmail(email: String) {
        val viewModel = SignUpViewModel()
        viewModel.createAccount("mona", "password")
        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }


    @Test
    fun invalidPassword(){
        val viewModel = SignUpViewModel()
        viewModel.createAccount("mona@gmail.com", "")
        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }
}