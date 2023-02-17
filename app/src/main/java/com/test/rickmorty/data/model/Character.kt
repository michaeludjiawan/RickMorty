package com.test.rickmorty.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @SerializedName("name") val name: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("species") val species: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("origin") val originData: LinkedInfo,
    @SerializedName("location") val locationData: LinkedInfo,
    @SerializedName("image") val avatarUrl: String = "",
    @SerializedName("episode") val episodeUrls: List<String> = emptyList(),
) : Parcelable