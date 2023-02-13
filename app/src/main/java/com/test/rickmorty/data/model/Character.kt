package com.test.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val originData: LinkedInfo,
    @SerializedName("location") val locationData: LinkedInfo,
    @SerializedName("image") val avatarUrl: String,
    @SerializedName("episode") val episodeUrls: List<String>,
)