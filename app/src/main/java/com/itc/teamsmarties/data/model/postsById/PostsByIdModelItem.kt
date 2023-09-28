package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class PostsByIdModelItem(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("kind")
    val kind: String
)