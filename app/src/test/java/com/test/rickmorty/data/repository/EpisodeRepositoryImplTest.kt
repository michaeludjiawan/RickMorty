package com.test.rickmorty.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.model.Episode
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
class EpisodeRepositoryImplTest {
    private lateinit var episodeRepository: EpisodeRepositoryImpl

    private lateinit var testDispatcher: TestDispatcher
    private val apiService = mock<ApiService>()

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        episodeRepository = EpisodeRepositoryImpl(apiService, testDispatcher)
    }

    @Test
    fun getEpisodes_fetchServer_matchArrayContentToString() = runTest(testDispatcher) {
        val ids = intArrayOf(1,2,3)
        episodeRepository.getEpisodes(ids)
        verify(apiService).getEpisodes(ids.contentToString())
    }

    @Test
    fun getEpisodes_responseExist_returnMatch() = runTest(testDispatcher) {
        val ids = intArrayOf(1,2,3)

        whenever(apiService.getEpisodes(ids.contentToString()))
            .thenReturn(listOf(
                Episode(id = 1, name = "Ep Test 1"),
                Episode(id = 2, name = "Ep Test 2"),
                Episode(id = 3, name = "Ep Test 3"),
            ))

        val result = episodeRepository.getEpisodes(ids)
        assert(result.getOrNull()!!.size == 3)

        assert(result.getOrNull()!![0].id == 1)
        assert(result.getOrNull()!![1].id == 2)
        assert(result.getOrNull()!![2].id == 3)
    }

    @Test
    fun getEpisodes_responseEmpty_returnEmpty() = runTest(testDispatcher) {
        val ids = intArrayOf(1,2,3)

        whenever(apiService.getEpisodes(ids.contentToString())).thenReturn(listOf())
        val result = episodeRepository.getEpisodes(ids)
        assert(result.getOrNull() == emptyList<Episode>())
    }

    @Test
    fun getEpisodes_responseNull_returnEmpty() = runTest(testDispatcher) {
        val ids = intArrayOf(1,2,3)

        whenever(apiService.getEpisodes(ids.contentToString())).thenReturn(null)
        val result = episodeRepository.getEpisodes(ids)
        assert(result.getOrNull() == emptyList<Episode>())
    }
}