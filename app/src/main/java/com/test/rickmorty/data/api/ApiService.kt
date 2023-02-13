package com.test.rickmorty.data.api

import com.test.rickmorty.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<PagedResponse<Character>>
}