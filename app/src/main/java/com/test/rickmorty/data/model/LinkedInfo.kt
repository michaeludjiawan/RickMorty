package com.test.rickmorty.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkedInfo(
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = ""
): Parcelable