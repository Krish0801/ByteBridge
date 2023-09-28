package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("after")
    val after: String? = "",
    @SerializedName("before")
    val before: Any? = Any(),
    @SerializedName("children")
    val children: List<ChildrenModel>? = listOf(),
    @SerializedName("dist")
    val dist: Int? = 0,
    @SerializedName("geo_filter")
    val geoFilter: String? = "",
    @SerializedName("modhash")
    val modhash: String? = ""
)