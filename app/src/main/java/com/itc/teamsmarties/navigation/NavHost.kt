package com.itc.teamsmarties.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itc.teamsmarties.domain.EndPointsViewModel
import com.itc.teamsmarties.ui.chat.ChatScreen
import com.itc.teamsmarties.ui.create.CreateScreen
import com.itc.teamsmarties.ui.home.DetailsScreen
import com.itc.teamsmarties.ui.home.HomeScreen
import com.itc.teamsmarties.ui.hub.HubScreen
import com.itc.teamsmarties.ui.inbox.InboxScreen
import com.itc.teamsmarties.ui.login.LoginScreen
import com.itc.teamsmarties.ui.signup.SignupScreen


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("SuspiciousIndentation", "NewApi")
@Composable
fun BottomNavGraph(navController: NavHostController) {

    val viewModel: EndPointsViewModel = hiltViewModel()
    val currentEndPoint = "Home"


    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                viewModel = viewModel,
                currentEndPoint = currentEndPoint,
                navController = navController
            )
        }
        composable(route = Screen.HubScreen.route) {
            HubScreen()
        }
        composable(route = Screen.CreateScreen.route) {
            CreateScreen()
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen()
        }
        composable(route = Screen.InboxScreen.route) {
            InboxScreen()
        }
        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }

        composable(route = Screen.SportScreen.route) {
            HomeScreen(
                viewModel = viewModel,
                currentEndPoint = "Sports",
                navController = navController
            )
        }
        composable(route = Screen.TechnologyScreen.route) {
            HomeScreen(
                viewModel = viewModel,
                currentEndPoint = "Technology",
                navController = navController
            )
        }
        composable(route = Screen.FoodScreen.route) {
            HomeScreen(
                viewModel = viewModel,
                currentEndPoint = "Food",
                navController = navController
            )

        }

        composable(route = "${Screen.DetailsScreen.route}/{postId}") {

            it.arguments?.getString("postId")
            DetailsScreen(
                it.arguments?.getString("postId")!!
            )

        }

    }

}
