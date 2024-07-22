package com.baharlou.buddies.signup.state

import com.baharlou.buddies.domain.user.User

sealed class SignUpState {
    data class SignedUp(val user: User) : SignUpState()
    object BadEmail : SignUpState()
    object BadPassword : SignUpState()
    object Valid : SignUpState()
    object DuplicateAccount : SignUpState()
    object BackendError : SignUpState()
    object OfflineError : SignUpState()

}
