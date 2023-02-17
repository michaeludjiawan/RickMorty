package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.util.NetworkUtil.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CharacterRepositoryImpl(
    private val apiService: ApiService,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : CharacterRepository {

    override suspend fun getAllCharacters(page: Int): Result<List<Character>> {
        return withContext(coroutineContext) {
            val result = safeApiCall { apiService.getAllCharacters(page) }

            return@withContext result.map {
                result.getOrNull()?.results.orEmpty()
            }
        }
    }
}