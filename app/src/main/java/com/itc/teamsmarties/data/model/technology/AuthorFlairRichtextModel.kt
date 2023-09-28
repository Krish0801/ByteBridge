package com.itc.teamsmarties.data.model.technology


import com.google.gson.annotations.SerializedName

data class AuthorFlairRichtextModel(
    @SerializedName("e")
    val e: String? = "",
    @SerializedName("t")
    val t: String? = ""
)