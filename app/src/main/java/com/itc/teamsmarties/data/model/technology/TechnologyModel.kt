package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class TechnologyModel(
    @SerializedName("data")
    val `data`: DataModel? = DataModel(),
    @SerializedName("kind")
    val kind: String? = ""
)