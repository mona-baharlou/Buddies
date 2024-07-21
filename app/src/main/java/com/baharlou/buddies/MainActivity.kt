package com.baharlou.buddies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baharlou.buddies.signup.SignUpScreen
import com.baharlou.buddies.signup.SignUpViewModel
import com.baharlou.buddies.timeline.TimelineScreen
import com.baharlou.buddies.ui.theme.BuddiesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val signUpViewModel: SignUpViewModel by viewModel<SignUpViewModel>()

    private companion object {
        private const val SIGN_UP = "signUp"
        private const val TIMELINE = "timeline"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            BuddiesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    NavHost(navController = navController, startDestination = SIGN_UP) {

                        composable(SIGN_UP) {
                            SignUpScreen(
                                signUpViewModel,
                                onSignedUp = { navController.navigate(TIMELINE) })
                        }

                        composable(TIMELINE) {
                            TimelineScreen()
                        }

                    }

                }
            }
        }
    }
}
