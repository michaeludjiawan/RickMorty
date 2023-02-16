package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Location
import com.test.rickmorty.util.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepositoryImpl(
    private val apiService: ApiService
) : LocationRepository {
    override suspend fun getLocations(ids: IntArray): Result<List<Location>> {
        return withContext(Dispatchers.IO) {
            NetworkUtil.safeApiCall { apiService.getLocations(ids.contentToString()) }
        }
    }
}