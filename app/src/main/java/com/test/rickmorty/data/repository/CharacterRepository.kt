package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Character

interface CharacterRepository {
    /**
     * Returns all characters list.
     * @param page page of list
     * @return list of characters from given page
     */
    fun getAllCharacters(page: Int): List<Character>
}