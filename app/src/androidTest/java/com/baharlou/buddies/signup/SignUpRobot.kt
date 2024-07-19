package com.baharlou.buddies.signup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.baharlou.buddies.MainActivity
import com.baharlou.buddies.R

fun launchSignUpScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignUpRobot.() -> Unit
): SignUpRobot {
    return SignUpRobot(rule).apply(block)
}


class SignUpRobot(
    val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    fun typeEmail(email: String) {
        val emailHint = rule.activity.getString(R.string.email)
        rule.onNodeWithText(emailHint)
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        val passwordHint = rule.activity.getString(R.string.password)
        rule.onNodeWithText(passwordHint)
            .performTextInput(password)
    }

    fun submit() {
        val signUp = rule.activity.getString(R.string.signup)
        rule.onNodeWithText(signUp)
            .performClick()
    }

    infix fun verify(
        block: SignUpVerification.() -> Unit
    ): SignUpVerification {
        return SignUpVerification(rule).apply(block)
    }


}

class SignUpVerification(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun timeLineScreenIsPresented() {
        val timeline = rule.activity.getString(R.string.timeline)

        rule.onNodeWithText(timeline).assertIsDisplayed()
    }

}


