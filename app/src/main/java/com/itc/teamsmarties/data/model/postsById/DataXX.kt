package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("after")
    val after: Any?,
    @SerializedName("before")
    val before: Any?,
    @SerializedName("children")
    val children: List<ChildrenX>,
    @SerializedName("dist")
    val dist: Any?,
    @SerializedName("geo_filter")
    val geoFilter: String,
    @SerializedName("modhash")
    val modhash: String
)