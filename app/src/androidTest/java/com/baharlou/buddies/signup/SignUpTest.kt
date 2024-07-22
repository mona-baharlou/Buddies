package com.baharlou.buddies.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.baharlou.buddies.MainActivity
import com.baharlou.buddies.domain.user.OffLineUser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class SignUpTest {

    @get:Rule
    val signupTestRule = createAndroidComposeRule<MainActivity>()

    private val offLineUser = OffLineUser()

    private val signUpModule = module {
        factory { offLineUser } //replacement for offlineUser that is created in application Module
    }

    @Before
    fun setUp() {
        loadKoinModules(signUpModule)
    }

    @Test
    fun performSignUp() {
        launchSignUpScreen(signupTestRule) {
            typeEmail("mona@gmail.com")
            typePassword("paSsword1@")
            submit()
        } verify {
            timeLineScreenIsPresented()
        }

    }


    @Test
    fun displayAccountAlreadyExistedError() {
        val signedUpUserEmail = "nimaa@gm.com"
        val signedUpUserPassword = "nImaa@123"

        offLineUser.createUser(signedUpUserEmail, signedUpUserPassword)

        launchSignUpScreen(signupTestRule) {
            typeEmail(signedUpUserEmail)
            typePassword(signedUpUserPassword)
            submit()
        } verify {
            accountAlreadyExistErrorIsShown()
        }
    }


}