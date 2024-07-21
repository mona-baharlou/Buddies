package com.baharlou.buddies.domain.user

import com.baharlou.buddies.domain.exceptions.DuplicateAccountException
import com.baharlou.buddies.signup.state.SignUpState

open class UserRepository(val offLineUser: OffLineUser) {
    fun signUp(
        email: String,
        password: String,
    ): SignUpState {
        val result = try {
            val user = offLineUser.createUser(email, password)
            SignUpState.SignedUp(user)
        } catch (duplicateAccount: DuplicateAccountException) {
            SignUpState.DuplicateAccount
        }
        return result
    }
}