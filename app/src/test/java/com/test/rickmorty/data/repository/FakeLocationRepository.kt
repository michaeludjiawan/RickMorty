package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Location

class FakeLocationRepository : LocationRepository {

    private var items = ArrayList<Location>()
    private var shouldReturnSuccess = true

    override suspend fun getLocations(ids: IntArray): Result<List<Location>> {
        return if (shouldReturnSuccess) {
            Result.success(items)
        } else {
            Result.failure(Exception())
        }
    }

    fun setItems(items: List<Location>) {
        this.items.clear()
        this.items.addAll(items)
    }

    fun setShouldReturnSuccess(isSuccess: Boolean) {
        shouldReturnSuccess = isSuccess
    }
}