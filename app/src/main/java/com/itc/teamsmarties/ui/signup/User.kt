package com.itc.teamsmarties.ui.signup

data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)