package com.itc.teamsmarties.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.itc.teamsmarties.ui.signup.SignupScreen
import com.itc.teamsmarties.ui.theme.TeamSmartiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TeamSmartiesTheme {
                SignupScreen(navController = navController)
            }
        }
    }
}