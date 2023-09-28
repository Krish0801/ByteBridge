package com.itc.teamsmarties.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.itc.teamsmarties.ui.login.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.itc.teamsmarties.ui.theme.TeamSmartiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        } else {
            setContent {
                TeamSmartiesTheme {
                    LoginScreen()
                }
            }
        }
    }


}
