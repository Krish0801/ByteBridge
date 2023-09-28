package com.itc.teamsmarties.data.model.sports


import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("resolutions")
    val resolutions: List<ResolutionModel?>? = listOf(),
    @SerializedName("source")
    val source: SourceModel? = SourceModel(),
    @SerializedName("variants")
    val variants: VariantsModel? = VariantsModel()
)