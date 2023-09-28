package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class ResolutionModel(
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)