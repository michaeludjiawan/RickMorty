package com.test.rickmorty.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private var mutableCharacters: MutableLiveData<List<Character>> = MutableLiveData()
    val characters: LiveData<List<Character>> = mutableCharacters

    private var mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = mutableIsLoading

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        mutableIsLoading.value = true

        viewModelScope.launch {
            val result = characterRepository.getAllCharacters(1)
            if (result.isSuccess) {
                mutableCharacters.value = result.getOrNull().orEmpty()
            } else {

            }

            mutableIsLoading.value = false
        }
    }
}