package com.baharlou.buddies.signup

import com.baharlou.buddies.domain.exceptions.BackendException
import com.baharlou.buddies.domain.exceptions.ConnectionUnavailableException
import com.baharlou.buddies.domain.user.User
import com.baharlou.buddies.domain.user.UserCatalog
import com.baharlou.buddies.domain.user.UserRepository
import com.baharlou.buddies.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FailedAccountCreationTest {


    @Test
    fun backendError() {
        val userRepository = UserRepository(UnavailableUserCatalog())
        val result = userRepository.signUp("email", "pass")
        assertEquals(SignUpState.BackendError, result)
    }

    @Test
    fun offlineError(){
        val repository = UserRepository(OfflineUserCatalog())
        val result = repository.signUp("email","password")
        assertEquals(SignUpState.OfflineError, result)
    }
}

class OfflineUserCatalog : UserCatalog {
    override fun createUser(email: String, password: String): User {
        throw ConnectionUnavailableException()
    }

}

//mimic a behavior that the backend throw an exception
class UnavailableUserCatalog : UserCatalog {
    override fun createUser(email: String, password: String): User {
        throw BackendException()
    }

}
