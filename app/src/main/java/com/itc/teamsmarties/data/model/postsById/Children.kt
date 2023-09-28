package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("kind")
    val kind: String
)