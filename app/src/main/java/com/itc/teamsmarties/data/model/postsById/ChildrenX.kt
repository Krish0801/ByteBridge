package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class ChildrenX(
    @SerializedName("data")
    val `data`: DataXXX,
    @SerializedName("kind")
    val kind: String
)