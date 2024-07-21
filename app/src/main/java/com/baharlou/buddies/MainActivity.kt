package com.baharlou.buddies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baharlou.buddies.signup.SignUpScreen
import com.baharlou.buddies.ui.theme.BuddiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            BuddiesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    NavHost(navController = navController, startDestination = "signUp") {

                        composable("signUp") {
                            SignUpScreen(onSignedUp = { navController.navigate("timeline") })
                        }

                        composable("timeline") {
                            Text(text = stringResource(id = R.string.timeline))
                        }

                    }

                }
            }
        }
    }
}
