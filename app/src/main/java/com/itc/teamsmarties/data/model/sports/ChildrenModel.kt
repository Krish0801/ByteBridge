package com.itc.teamsmarties.data.model.sports


import com.google.gson.annotations.SerializedName

data class ChildrenModel(
    @SerializedName("data")
    val `data`: DataModelX? = DataModelX(),
    @SerializedName("kind")
    val kind: String? = ""
)