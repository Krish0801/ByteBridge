package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("kind")
    val kind: String = ""
)