package com.itc.teamsmarties.data.model.crypto


import com.google.gson.annotations.SerializedName

data class ResizedStaticIconModel(
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)