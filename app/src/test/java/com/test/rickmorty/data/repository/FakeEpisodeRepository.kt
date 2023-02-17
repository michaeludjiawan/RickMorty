package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Episode

class FakeEpisodeRepository : EpisodeRepository {

    private var items = ArrayList<Episode>()
    private var shouldReturnSuccess = true

    override suspend fun getEpisodes(ids: IntArray): Result<List<Episode>> {
        return if (shouldReturnSuccess) {
            Result.success(items)
        } else {
            Result.failure(Exception())
        }
    }

    fun setItems(items: List<Episode>) {
        this.items.clear()
        this.items.addAll(items)
    }

    fun setShouldReturnSuccess(isSuccess: Boolean) {
        shouldReturnSuccess = isSuccess
    }
}