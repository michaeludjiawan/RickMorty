package com.test.rickmorty.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.data.model.Location
import com.test.rickmorty.data.repository.EpisodeRepository
import com.test.rickmorty.data.repository.LocationRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val episodeRepository: EpisodeRepository, private val locationRepository: LocationRepository
) : ViewModel() {

    private val mutableCharacter: MutableLiveData<Character> = MutableLiveData()
    val character: LiveData<Character> = mutableCharacter

    private val mutableEpisodes: MutableLiveData<List<Episode>> = MutableLiveData()
    val episodes: LiveData<List<Episode>> = mutableEpisodes

    private val mutableLocation: MutableLiveData<Location> = MutableLiveData()
    val location: LiveData<Location> = mutableLocation

    private val mutableOrigin: MutableLiveData<Location> = MutableLiveData()
    val origin: LiveData<Location> = mutableOrigin

    private var originId = -1
    private var locationId = -1

    fun setData(character: Character) {
        mutableCharacter.value = character

        viewModelScope.launch {
            launch {
                originId = extractIdFromUrl(character.originData.url)
                locationId = extractIdFromUrl(character.locationData.url)
                getLocationsByIds(intArrayOf(originId, locationId))
            }

            launch {
                val epIds = extractIdsFromUrls(character.episodeUrls)
                getEpisodesByIds(epIds)
            }
        }
    }

    private fun extractIdsFromUrls(urls: List<String>): IntArray {
        return urls.map { url ->
            extractIdFromUrl(url)
        }.toIntArray()
    }

    private fun extractIdFromUrl(url: String): Int {
        return try {
            url.substring(url.lastIndexOf('/') + 1).toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    private suspend fun getEpisodesByIds(ids: IntArray) {
        val result = episodeRepository.getEpisodes(ids)

        if (result.isSuccess) {
            mutableEpisodes.value = result.getOrNull().orEmpty()
        } else {

        }
    }

    private suspend fun getLocationsByIds(ids: IntArray) {
        val result = locationRepository.getLocations(ids)

        if (result.isSuccess) {
            mutableOrigin.value = result.getOrNull().orEmpty().find { it.id == originId }
            mutableLocation.value = result.getOrNull().orEmpty().find { it.id == locationId }
        } else {

        }
    }
}