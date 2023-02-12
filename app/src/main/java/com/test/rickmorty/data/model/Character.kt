package com.test.rickmorty.data.model

data class Character(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originData: Pair<String, String>,
    val locationData: Pair<String, String>,
    val avatarUrl: String,
    val episodeUrls: List<String>,
)
