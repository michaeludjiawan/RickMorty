package com.test.rickmorty.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.rickmorty.data.model.Character

class CharacterDetailViewModel: ViewModel() {

    private val mutableCharacter: MutableLiveData<Character> = MutableLiveData()
    val character: LiveData<Character> = mutableCharacter

    fun setData(character: Character) {
        mutableCharacter.value = character
    }
}