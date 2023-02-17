package com.test.rickmorty.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.rickmorty.awaitNextValue
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.data.model.LinkedInfo
import com.test.rickmorty.data.model.Location
import com.test.rickmorty.data.repository.FakeEpisodeRepository
import com.test.rickmorty.data.repository.FakeLocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class CharacterDetailViewModelTest {

    private lateinit var viewModel: CharacterDetailViewModel
    private val locationRepository = FakeLocationRepository()
    private val episodeRepository = FakeEpisodeRepository()

    private val testEpisodes = listOf(
        Episode(id = 1, name = "Ep Test 1"),
        Episode(id = 2, name = "Ep Test 2"),
        Episode(id = 3, name = "Ep Test 3"),
    )
    private val testOrigin = Location(
        id = 1,
        name = "Location Test 1",
        url = "https://rickandmortyapi.com/api/location/1"
    )
    private val testLocation = Location(
        id = 2,
        name = "Location Test 2",
        url = "https://rickandmortyapi.com/api/location/2"
    )
    private val testCharacter = Character(
        name = "Test Character 1",
        originData = LinkedInfo(name = testOrigin.name, url = testOrigin.url),
        locationData = LinkedInfo(name = testLocation.name, url = testLocation.url)
    )

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        episodeRepository.setItems(testEpisodes)
        locationRepository.setItems(listOf(testOrigin, testLocation))

        viewModel = CharacterDetailViewModel(episodeRepository, locationRepository)
    }

    @Test
    fun setData_getOrigin_matchValues() = runTest {
        viewModel.setData(testCharacter)

        assert(viewModel.origin.awaitNextValue().id == testOrigin.id)
    }

    @Test
    fun setData_getLocation_matchValues() = runTest {
        viewModel.setData(testCharacter)

        assert(viewModel.location.awaitNextValue().id == testLocation.id)
    }

    @Test
    fun setData_getEpisodes_matchValues() = runTest {
        viewModel.setData(testCharacter)

        assert(viewModel.episodes.awaitNextValue().size == 3)
        assert(viewModel.episodes.awaitNextValue().contains(testEpisodes[0]))
        assert(viewModel.episodes.awaitNextValue().contains(testEpisodes[1]))
        assert(viewModel.episodes.awaitNextValue().contains(testEpisodes[2]))
    }
}