package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class PModel(
    @SerializedName("u")
    val u: String? = "",
    @SerializedName("x")
    val x: Int? = 0,
    @SerializedName("y")
    val y: Int? = 0
)