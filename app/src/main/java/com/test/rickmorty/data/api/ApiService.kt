package com.test.rickmorty.data.api

import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.data.model.Location
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): PagedResponse<Character>

    @GET("episode/{ids}")
    suspend fun getEpisodes(
        @Path(value = "ids", encoded = true) ids: String
    ): List<Episode>

    @GET("location/{ids}")
    suspend fun getLocations(
        @Path(value = "ids", encoded = true) ids: String
    ): List<Location>
}