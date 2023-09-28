package com.itc.teamsmarties.navigation

import com.itc.teamsmarties.R

sealed class Screen(val route: String, val name: String, val icon: Int) {
    object HomeScreen : Screen("home_screen", "Home", R.drawable.ic_home)
    object HubScreen : Screen("hub_screen", "Hub", R.drawable.ic_communities)
    object CreateScreen : Screen("create_screen", "Create", R.drawable.ic_create)
    object ChatScreen : Screen("chat_screen", "Chat", R.drawable.ic_chat)
    object InboxScreen : Screen("inbox_screen", "Inbox", R.drawable.ic_inbox)

    object LoginScreen : Screen("login_screen", "Login", R.drawable.ic_login)

    object SignupScreen : Screen("signup_screen", "SignUp", R.drawable.ic_login)
    object SportScreen : Screen("sport_screen", "r/Sport", R.drawable.ic_sport)
    object TechnologyScreen : Screen("technology_screen", "r/Technology", R.drawable.ic_technology)
    object FoodScreen : Screen("food_screen", "r/Food", R.drawable.ic_food)

    object DetailsScreen : Screen("details_screen", "Details Screen", R.drawable.ic_home)
}
