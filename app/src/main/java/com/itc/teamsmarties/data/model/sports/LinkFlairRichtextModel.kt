package com.itc.teamsmarties.data.model.sports


import com.google.gson.annotations.SerializedName

data class LinkFlairRichtextModel(
    @SerializedName("a")
    val a: String? = "",
    @SerializedName("e")
    val e: String? = "",
    @SerializedName("t")
    val t: String? = "",
    @SerializedName("u")
    val u: String? = ""
)