package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.util.NetworkUtil.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class EpisodeRepositoryImpl(
    private val apiService: ApiService,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : EpisodeRepository {
    override suspend fun getEpisodes(ids: IntArray): Result<List<Episode>> {
        return withContext(coroutineContext) {
            val result = safeApiCall { apiService.getEpisodes(ids.contentToString()) }

            return@withContext result.map {
                result.getOrNull().orEmpty()
            }
        }
    }
}