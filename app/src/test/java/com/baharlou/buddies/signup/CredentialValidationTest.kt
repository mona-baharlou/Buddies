package com.baharlou.buddies.signup

import com.baharlou.buddies.InstantTaskExecutorExtension
import com.baharlou.buddies.domain.user.OffLineUser
import com.baharlou.buddies.domain.user.UserRepository
import com.baharlou.buddies.domain.validation.CredentialValidationResult
import com.baharlou.buddies.domain.validation.RegexValidator
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
        val viewModel = SignUpViewModel(RegexValidator(), UserRepository(OffLineUser()))
        viewModel.createAccount("mona", "password")
        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }


    @ParameterizedTest
    @CsvSource(
        "''",
        "'      '",
        "'12345678'",
        "'abcd1234'",
        "'abCD1234'",
        "'abcd1234@#'",
        "'ABCDE1234@#'",
    )

    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel(RegexValidator(), UserRepository(OffLineUser()))
        viewModel.createAccount("mona@gmail.com", password)
        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }


    @Test
    fun validEmailAndPassword(){
        val validator = RegexValidator()
        val result = validator.validate("mona@gmail.com", "abC123@#")
        assertEquals(CredentialValidationResult.Valid, result)
    }

}