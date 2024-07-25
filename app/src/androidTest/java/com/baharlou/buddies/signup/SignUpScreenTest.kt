package com.baharlou.buddies.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.baharlou.buddies.MainActivity
import com.baharlou.buddies.domain.exceptions.BackendException
import com.baharlou.buddies.domain.exceptions.ConnectionUnavailableException
import com.baharlou.buddies.domain.user.OffLineUser
import com.baharlou.buddies.domain.user.User
import com.baharlou.buddies.domain.user.UserCatalog
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class SignUpScreenTest {

    @get:Rule
    val signupTestRule = createAndroidComposeRule<MainActivity>()

    private val offLineUser = OffLineUser()

    private val signUpModule = module {
        factory<UserCatalog> { offLineUser } //replacement for offlineUser that is created in application Module
    }

    @Before
    fun setUp() {
        loadKoinModules(signUpModule)
    }

    @Test
    fun performSignUp() {
        launchSignUpScreen(signupTestRule) {
            typeEmail("monaa@gmail.com")
            typePassword("paSsword1@")
            submit()
        } verify {
            timeLineScreenIsPresented()
        }

    }


    @Test
    fun displayAccountAlreadyExistedError() {
        val signedUpUserEmail = "nima@gm.com"
        val signedUpUserPassword = "nIma@123"

        offLineUser.createUser(signedUpUserEmail, signedUpUserPassword)

        launchSignUpScreen(signupTestRule) {
            typeEmail(signedUpUserEmail)
            typePassword(signedUpUserPassword)
            submit()
        } verify {
            accountAlreadyExistErrorIsShown()
        }
    }

    @Test
    fun displayBadEmailError(){
        launchSignUpScreen(signupTestRule){
            typeEmail("emial")
            submit()
        } verify{
            badEmailErrorIsShown()
        }
    }

    @Test
    fun displayBadPasswordError(){
        launchSignUpScreen(signupTestRule){
            typeEmail("mona@gm.com")
            typePassword("123")
            submit()
        } verify {
            badPasswordErrorIsShown()
        }
    }

    @Test
    fun displayBackendError() {
        val replaceModule = module {
            factory<UserCatalog> { UnavailableUserCatalog() }
        }

        //unloadKoinModules(signUpModule)
        loadKoinModules(replaceModule)

        launchSignUpScreen(signupTestRule) {
            typeEmail("mona@gm.com")
            typePassword("moNa123#")
            submit()
        } verify {
            backendErrorIsShown()
        }
    }


    @Test
    fun displayOfflineError() {

        unloadKoinModules(signUpModule)

        val replaceModule = module {
            single<UserCatalog> { OfflineUserCatalog() }
        }

        loadKoinModules(replaceModule)

        launchSignUpScreen(signupTestRule) {
            typeEmail("hima@gm.com")
            typePassword("hIM@1234")
            submit()
        } verify {
            offlineErrorIsShown()
        }
    }

    @After
    fun tearDown() {
        val resetModule = module {
            single { OffLineUser() }
        }
        loadKoinModules(resetModule)
    }

    //mimic a behavior that the backend throw an exception
    class UnavailableUserCatalog : UserCatalog {
        override fun createUser(email: String, password: String): User {
            throw BackendException()
        }
    }

    class OfflineUserCatalog : UserCatalog {
        override fun createUser(email: String, password: String): User {
            throw ConnectionUnavailableException()
        }
    }

}
