package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.util.NetworkUtil.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodeRepositoryImpl(
    private val apiService: ApiService
) : EpisodeRepository {
    override suspend fun getEpisodes(ids: IntArray): Result<List<Episode>> {
        return withContext(Dispatchers.IO) {
            safeApiCall { apiService.getEpisodes(ids.contentToString()) }
        }
    }
}