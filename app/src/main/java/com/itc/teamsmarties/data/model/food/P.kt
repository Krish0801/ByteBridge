package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class P(
    @SerializedName("u")
    val u: String = "",
    @SerializedName("x")
    val x: Int = 0,
    @SerializedName("y")
    val y: Int = 0
)