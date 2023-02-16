package com.test.rickmorty.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.data.repository.EpisodeRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    private val mutableCharacter: MutableLiveData<Character> = MutableLiveData()
    val character: LiveData<Character> = mutableCharacter

    private val mutableEpisodes: MutableLiveData<List<Episode>> = MutableLiveData()
    val episodes: LiveData<List<Episode>> = mutableEpisodes

    fun setData(character: Character) {
        mutableCharacter.value = character

        viewModelScope.launch {
            val epIds = extractIdFromUrl(character.episodeUrls)
            getEpisodesByIds(epIds)
        }
    }

    private fun extractIdFromUrl(urls: List<String>): IntArray {
        return urls.map {  url ->
            url.substring(url.lastIndexOf('/') + 1).toInt()
        }.toIntArray()
    }

    private suspend fun getEpisodesByIds(ids: IntArray) {
        val result = episodeRepository.getEpisodes(ids)

        if (result.isSuccess) {
            mutableEpisodes.value = result.getOrNull().orEmpty()
        } else {

        }
    }
}