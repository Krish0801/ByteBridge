package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("caption")
    val caption: String? = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("media_id")
    val mediaId: String = ""
)