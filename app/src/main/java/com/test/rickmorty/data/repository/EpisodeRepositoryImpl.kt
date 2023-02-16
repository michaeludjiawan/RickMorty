package com.test.rickmorty.data.repository

import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.util.NetworkUtil.safeApiCall

class EpisodeRepositoryImpl(
    private val apiService: ApiService
) : EpisodeRepository {
    override suspend fun getEpisodes(ids: IntArray): Result<List<Episode>> {
        val episodes = safeApiCall { apiService.getEpisodes(ids.contentToString()) }

        return episodes
    }
}