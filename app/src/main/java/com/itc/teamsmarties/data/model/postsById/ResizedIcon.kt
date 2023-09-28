package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class ResizedIcon(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)