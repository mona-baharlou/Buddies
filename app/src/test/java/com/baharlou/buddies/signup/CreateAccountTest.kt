package com.baharlou.buddies.signup

import com.baharlou.buddies.InstantTaskExecutorExtension
import com.baharlou.buddies.domain.user.User
import com.baharlou.buddies.domain.validation.RegexValidator
import com.baharlou.buddies.signup.state.SignUpState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CreateAccountTest {

    @Test
    fun accountCreated() {
        val viewModel = SignUpViewModel(RegexValidator())
        viewModel.createAccount("mona@gm.com", "Mona12@#")

        val name = User("id", "mona@gm.com")

        Assertions.assertEquals(SignUpState.SignedUp(name), viewModel.signUpState.value)
    }
}