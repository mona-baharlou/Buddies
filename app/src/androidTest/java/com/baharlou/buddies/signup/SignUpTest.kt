package com.baharlou.buddies.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.baharlou.buddies.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpTest {

    @get:Rule
    val signupTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun performSignUp(){
        launchSignUpScreen(signupTestRule){
            typeEmail("mona@gmail.com")
            typePassword("paSsword1@")
            submit()
        } verify{
            timeLineScreenIsPresented()
        }

    }
}