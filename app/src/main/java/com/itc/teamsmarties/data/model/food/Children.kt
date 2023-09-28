package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    val `data`: DataX = DataX(),
    @SerializedName("kind")
    val kind: String = ""
)