package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.util.NetworkUtil.safeApiCall

class CharacterRepositoryImpl(
    private val apiService: ApiService
) : CharacterRepository {

    override suspend fun getAllCharacters(page: Int): Result<List<Character>> {
        val result = safeApiCall { apiService.getAllCharacters(page) }

        return result.map {
            it.results
        }
    }
}