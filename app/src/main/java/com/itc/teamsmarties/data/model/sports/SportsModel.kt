package com.itc.teamsmarties.data.model.sports


import com.google.gson.annotations.SerializedName

data class SportsModel(
    @SerializedName("data")
    val `data`: DataModel? = DataModel(),
    @SerializedName("kind")
    val kind: String? = ""
)