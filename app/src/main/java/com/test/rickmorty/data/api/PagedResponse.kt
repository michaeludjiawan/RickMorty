package com.test.rickmorty.data.api

import com.google.gson.annotations.SerializedName

class PagedResponse<out T>(
    @SerializedName("info") val info: PagedInfo,
    @SerializedName("results") val results: List<T>
)
