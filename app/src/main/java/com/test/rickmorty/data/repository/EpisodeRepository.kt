package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Episode

interface EpisodeRepository {

    /**
     * Returns all episodes list with given ids.
     * @param ids list of episode ids
     * @return list of episodes from given ids
     */
    suspend fun getEpisodes(ids: IntArray): Result<List<Episode>>
}