package com.baharlou.buddies.signup.state

sealed class SignUpState {
    object BadEmail : SignUpState()
    object BadPassword:SignUpState()

}
