package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class RepliesX(
    @SerializedName("data")
    val `data`: DataXXXX,
    @SerializedName("kind")
    val kind: String
)