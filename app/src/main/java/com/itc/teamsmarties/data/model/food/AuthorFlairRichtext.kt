package com.itc.teamsmarties.data.model.food


import com.google.gson.annotations.SerializedName

data class AuthorFlairRichtext(
    @SerializedName("a")
    val a: String? = "",
    @SerializedName("e")
    val e: String = "",
    @SerializedName("t")
    val t: String? = "",
    @SerializedName("u")
    val u: String? = ""
)