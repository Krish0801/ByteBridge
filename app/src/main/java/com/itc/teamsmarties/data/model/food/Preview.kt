package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("enabled")
    val enabled: Boolean = false,
    @SerializedName("images")
    val images: List<Image> = listOf()
)