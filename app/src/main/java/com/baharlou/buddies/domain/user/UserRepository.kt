package com.baharlou.buddies.domain.user

import com.baharlou.buddies.domain.exceptions.BackendException
import com.baharlou.buddies.domain.exceptions.ConnectionUnavailableException
import com.baharlou.buddies.domain.exceptions.DuplicateAccountException
import com.baharlou.buddies.signup.state.SignUpState

class UserRepository(private val userCatalog: UserCatalog) {
    fun signUp(
        email: String,
        password: String,
    ): SignUpState {
        val result = try {
            val user = userCatalog.createUser(email, password)
            SignUpState.SignedUp(user)
        } catch (duplicateAccount: DuplicateAccountException) {
            SignUpState.DuplicateAccount
        } catch (backendException: BackendException) {
            SignUpState.BackendError
        } catch (connectionUnavailableException: ConnectionUnavailableException) {
            SignUpState.OfflineError
        }
        return result
    }
}