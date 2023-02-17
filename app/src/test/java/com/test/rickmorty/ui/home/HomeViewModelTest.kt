package com.test.rickmorty.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.rickmorty.awaitNextValue
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.LinkedInfo
import com.test.rickmorty.data.repository.FakeCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class HomeViewModelTest {

    private val characterRepository = FakeCharacterRepository()

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel = HomeViewModel(characterRepository)

        characterRepository.setItems(
            listOf(
                Character(name = "Test Name 1", locationData = LinkedInfo(), originData = LinkedInfo()),
                Character(name = "Test Name 2", locationData = LinkedInfo(), originData = LinkedInfo())
            )
        )
    }

    @Test
    fun getAllCharacters_success_charactersMatch() = runTest {
        runCurrent()

        assert(viewModel.characters.awaitNextValue().size == 2)
        assert(viewModel.characters.awaitNextValue()[0].name == "Test Name 1")
        assert(viewModel.characters.awaitNextValue()[1].name == "Test Name 2")
    }

    @Test
    fun getAllCharacters_success_toggleLoading() = runTest {
        assert(viewModel.isLoading.awaitNextValue())

        advanceUntilIdle()

        assert(!viewModel.isLoading.awaitNextValue())
    }
}