package com.test.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("name") val name: String = "",
    @SerializedName("air_date") val airDate: String = "",
    @SerializedName("episode") val episode: String = "",
    @SerializedName("characters") val charactersUrl: List<String> = listOf(),
    @SerializedName("url") val url: String = "",
    @SerializedName("created") val created: String = ""
)
