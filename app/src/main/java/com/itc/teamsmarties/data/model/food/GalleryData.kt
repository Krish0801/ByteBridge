package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class GalleryData(
    @SerializedName("items")
    val items: List<Item> = listOf()
)