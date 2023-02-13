package com.test.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class LinkedInfo(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)