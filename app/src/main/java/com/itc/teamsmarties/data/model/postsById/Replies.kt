package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class Replies(
    @SerializedName("data")
    val `data`: DataXX,
    @SerializedName("kind")
    val kind: String
)