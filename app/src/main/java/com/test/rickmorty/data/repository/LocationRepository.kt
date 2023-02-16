package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Location

interface LocationRepository {
    /**
     * Returns all locations list based on given ids.
     * @param ids list of id
     * @return list of locations with given ids
     */
    suspend fun getLocations(ids: IntArray): Result<List<Location>>
}