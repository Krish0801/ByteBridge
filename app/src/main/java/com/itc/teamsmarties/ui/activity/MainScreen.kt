package com.itc.teamsmarties.ui.activity

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itc.teamsmarties.R
import com.itc.teamsmarties.navigation.BottomNavGraph
import com.itc.teamsmarties.navigation.Screen
import com.itc.teamsmarties.ui.sideDrawer.Drawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(
            initialValue = DrawerValue.Closed
        )
    )
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route // Get the current route


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            TopBar(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        drawerContent = {
            Drawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
                currentRoute = currentRoute
            )
        },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun BottomBar(navController: NavHostController, isDetailScreen: Boolean = false) {
    val screens = listOf(
        Screen.HomeScreen,
        Screen.HubScreen,
        Screen.CreateScreen,
        Screen.ChatScreen,
        Screen.InboxScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var userComment by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser? = firebaseAuth.currentUser
    val userName: String = currentUser?.email?.substringBefore('@') ?: ""

    if (!isDetailScreen && currentDestination?.route != stringResource(R.string.details_screen_postId)) {
        BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

    if (isDetailScreen || currentDestination?.route == stringResource(R.string.details_screen_postId)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background, // Set your desired background color here
            shape = MaterialTheme.shapes.small
        ) {
            OutlinedTextField(
                value = userComment,
                onValueChange = {
                    userComment = it
                    showError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 2.dp, vertical = 4.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.clickable {
                            if (userComment.isNotEmpty()) {
                                userComment = ""
                                keyboardController?.hide()
                                Toast.makeText(
                                    context,
                                    "$userName:Comment posted successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                showError = true
                            }
                        }
                    )
                },
                placeholder = {
                    if (showError) {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text("Comment cannot be empty", color = Color.Red)
                        }
                    } else {
                        Text("Add a comment...")
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val isSelected = currentDestination?.route == screen.route
    val scale by animateFloatAsState(if (isSelected) 1.2f else 1.2f)

    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route)
        },
        modifier = Modifier
            .animateContentSize()
            .scale(scale),
        label = {
            Text(
                text = screen.name,
                fontSize = 8.sp,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        },
        icon = {
            val iconDrawable = painterResource(id = screen.icon)
            Icon(
                painter = iconDrawable,
                contentDescription = screen.name,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    )
}


@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val context = LocalContext.current
    val currentRoute = navController.currentDestination?.route

    var isDetailsScreen = false

    if (currentRoute == stringResource(R.string.details_screen_postId))
        isDetailsScreen = true

    TopAppBar(
        title = {
            Text(
                text = "ByteBridge",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            if (isDetailsScreen) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            } else {
                IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                    Icon(Icons.Filled.Menu, "Menu")
                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        actions = {
            IconButton(onClick = {
                val message = "Thank you for subscribing Reddit \uD83D\uDC96"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(R.drawable.reddit_splash),
                    contentDescription = "Custom Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    )
}




