package com.baharlou.buddies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.baharlou.buddies.signup.SignUpScreen
import com.baharlou.buddies.ui.theme.BuddiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuddiesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {



                    SignUpScreen() {
                        //Navigate to TimeLineScreen
                    }
                }
            }
        }
    }
}
