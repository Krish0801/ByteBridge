package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("resolutions")
    val resolutions: List<Resolution> = listOf(),
    @SerializedName("source")
    val source: Source = Source(),
    @SerializedName("variants")
    val variants: Variants? = Variants()
)