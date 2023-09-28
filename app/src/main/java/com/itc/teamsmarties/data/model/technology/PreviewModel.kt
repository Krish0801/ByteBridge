package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class PreviewModel(
    @SerializedName("enabled")
    val enabled: Boolean? = false,
    @SerializedName("images")
    val images: List<ImageModel?>? = listOf()
)