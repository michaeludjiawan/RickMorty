package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Location
import com.test.rickmorty.util.NetworkUtil.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LocationRepositoryImpl(
    private val apiService: ApiService,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : LocationRepository {
    override suspend fun getLocations(ids: IntArray): Result<List<Location>> {
        return withContext(coroutineContext) {
            val result = safeApiCall { apiService.getLocations(ids.contentToString()) }

            return@withContext result.map {
                result.getOrNull().orEmpty()
            }
        }
    }
}