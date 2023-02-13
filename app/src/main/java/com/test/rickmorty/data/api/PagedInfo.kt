package com.test.rickmorty.data.api

import com.google.gson.annotations.SerializedName

data class PagedInfo(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val nextUrl: String,
    @SerializedName("prev") val prevUrl: String
)
