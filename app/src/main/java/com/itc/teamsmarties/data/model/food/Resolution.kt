package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class Resolution(
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)