package com.itc.teamsmarties.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itc.teamsmarties.R
import kotlinx.coroutines.delay


class SplashScreen: ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

}
@Composable
fun SplashScreen(navigateTo:() -> Unit){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Add your splash screen content here, such as a logo or image
        Image(
            painter = painterResource(id = R.drawable.reddit_splash),
            contentDescription = null,
           modifier = Modifier.height(100.dp).width(100.dp)
        )
    }

    LaunchedEffect(Unit) {
        // Simulate a delay for the splash screen
        delay(1000)

        // Navigate to the next activity
        navigateTo()
    }
}