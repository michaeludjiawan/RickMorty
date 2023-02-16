package com.test.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("residents") val residentsUrl: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)
