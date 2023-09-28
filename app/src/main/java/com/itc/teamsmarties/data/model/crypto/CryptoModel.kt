package com.itc.teamsmarties.data.model.crypto


import com.google.gson.annotations.SerializedName

data class CryptoModel(
    @SerializedName("data")
    val `data`: DataModel? = DataModel(),
    @SerializedName("kind")
    val kind: String? = ""
)