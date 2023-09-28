package com.itc.teamsmarties.ui.sideDrawer

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itc.teamsmarties.R
import com.itc.teamsmarties.navigation.Screen
import com.itc.teamsmarties.ui.activity.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    currentRoute: String?, // Add currentRoute as a parameter
) {
    val items = listOf(
        Screen.SportScreen,
        Screen.TechnologyScreen,
        Screen.FoodScreen,
    )

    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser? = firebaseAuth.currentUser

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colorScheme.onSecondary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                )
                if (currentUser != null) {
                    val userName: String? = currentUser.email?.substringBefore('@') ?: ""

                    if (userName != null) {
                        Text(
                            text = "$userName",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(4.dp),
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "Username not available ",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                } else {
                    Text(
                        text = "Username is not signed in ",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        // Separate DrawerItem for HomeScreen outside of the loop
        DrawerItem(
            item = Screen.HomeScreen,
            selected = currentRoute == Screen.HomeScreen.route,
            onItemClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    // Pop up to the start destination to avoid building up a large stack
                    // of destinations and start fresh from the HomeScreen
                    popUpTo(navController.graph.startDestinationId)
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        )

        items.forEach { item ->
            // Other items in the drawer (SportScreen, TechnologyScreen, CryptoScreen)
            DrawerItem(
                item = item,
                selected = currentRoute == item.route,
                onItemClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        LogoutButton()
    }
}

@Composable
fun LogoutButton() {
    val context = LocalContext.current
    var isDialogOpen by remember { mutableStateOf(false) }

    Text(
        text = "Logout",
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(18.dp)
            .clickable {
                // Show the logout confirmation dialog
                isDialogOpen = true
            }
            .fillMaxWidth()
    )
    // Display the logout confirmation dialog if it is open
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog if the user taps outside it
                isDialogOpen = false
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Add your image here
                    Text(
                        text = "Confirm Logout",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(R.drawable.reddit_splash),
                        contentDescription = "Your Image",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            text = {
                Text(
                    text = "Are you sure you want to log out?",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        //Logout
                        Firebase.auth.signOut()

                        // Perform logout action here
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                        (context as Activity).finish()
                        Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT)
                            .show()
                        // Dismiss the dialog after logout
                        isDialogOpen = false
                    },
                    colors = textButtonColors(
                        backgroundColor = Color.Gray,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Yes",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isDialogOpen = false
                    },
                    colors = textButtonColors(
                        backgroundColor = Color.Gray,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        )
    }
}
