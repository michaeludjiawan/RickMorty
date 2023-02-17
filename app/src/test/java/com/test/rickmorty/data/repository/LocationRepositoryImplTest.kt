package com.test.rickmorty.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Location
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class LocationRepositoryImplTest {
    private lateinit var locationRepository: LocationRepositoryImpl

    private lateinit var testDispatcher: TestDispatcher
    private val apiService = mock<ApiService>()

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        locationRepository = LocationRepositoryImpl(apiService, testDispatcher)
    }

    @Test
    fun getLocations_fetchServer_matchArrayContentToString() = runTest(testDispatcher) {
        val ids = intArrayOf(1, 2, 3)
        locationRepository.getLocations(ids)
        verify(apiService).getLocations(ids.contentToString())
    }

    @Test
    fun getLocations_responseExist_returnMatch() = runTest(testDispatcher) {
        val ids = intArrayOf(1, 2, 3)

        whenever(apiService.getLocations(ids.contentToString()))
            .thenReturn(
                listOf(
                    Location(id = 1, name = "Location Test 1"),
                    Location(id = 2, name = "Location Test 2"),
                    Location(id = 3, name = "Location Test 3"),
                )
            )

        val result = locationRepository.getLocations(ids)
        assert(result.getOrNull()!!.size == 3)

        assert(result.getOrNull()!![0].id == 1)
        assert(result.getOrNull()!![1].id == 2)
        assert(result.getOrNull()!![2].id == 3)
    }

    @Test
    fun getLocations_responseEmpty_returnEmpty() = runTest(testDispatcher) {
        val ids = intArrayOf(1, 2, 3)

        whenever(apiService.getLocations(ids.contentToString())).thenReturn(listOf())
        val result = locationRepository.getLocations(ids)
        assert(result.getOrNull() == emptyList<Location>())
    }

    @Test
    fun getLocations_responseNull_returnEmpty() = runTest(testDispatcher) {
        val ids = intArrayOf(1, 2, 3)

        whenever(apiService.getLocations(ids.contentToString())).thenReturn(null)
        val result = locationRepository.getLocations(ids)
        assert(result.getOrNull() == emptyList<Location>())
    }
}