package com.test.rickmorty.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.rickmorty.data.api.ApiService
import com.test.rickmorty.data.api.PagedInfo
import com.test.rickmorty.data.api.PagedResponse
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.LinkedInfo
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
class CharacterRepositoryImplTest {
    private lateinit var characterRepository: CharacterRepositoryImpl

    private lateinit var testDispatcher: TestDispatcher
    private val apiService = mock<ApiService>()

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        characterRepository = CharacterRepositoryImpl(apiService, testDispatcher)
    }

    @Test
    fun getAllCharacters_fetchServer() = runTest(testDispatcher) {
        characterRepository.getAllCharacters(1)

        verify(apiService).getAllCharacters(1)
    }

    @Test
    fun getAllCharacters_responseExist_returnMatch() = runTest(testDispatcher) {
        whenever(apiService.getAllCharacters(1))
            .thenReturn(PagedResponse(PagedInfo(1, 1, "", ""), listOf(
                Character(name ="Test Name 1", locationData = LinkedInfo(), originData = LinkedInfo()),
                Character(name ="Test Name 2", locationData = LinkedInfo(), originData = LinkedInfo())
            )))

        val result = characterRepository.getAllCharacters(1)
        assert(result.getOrNull()!!.size == 2)

        assert(result.getOrNull()!![0].name == "Test Name 1")
    }

    @Test
    fun getAllCharacters_responseEmpty_returnEmpty() = runTest(testDispatcher) {
        whenever(apiService.getAllCharacters(1))
            .thenReturn(PagedResponse(PagedInfo(1, 1, "", ""), listOf()))

        val result = characterRepository.getAllCharacters(1)
        assert(result.getOrNull() == emptyList<Character>())
    }

    @Test
    fun getAllCharacters_responseNull_returnEmpty() = runTest(testDispatcher) {
        whenever(apiService.getAllCharacters(1)).thenReturn(null)

        val result = characterRepository.getAllCharacters(1)
        assert(result.getOrNull() == emptyList<Character>())
    }
}