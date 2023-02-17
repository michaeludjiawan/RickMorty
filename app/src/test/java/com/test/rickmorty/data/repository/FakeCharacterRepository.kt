package com.test.rickmorty.data.repository

import com.test.rickmorty.data.model.Character

class FakeCharacterRepository : CharacterRepository {

    private var items = ArrayList<Character>()
    private var shouldReturnSuccess = true

    override suspend fun getAllCharacters(page: Int): Result<List<Character>> {
        return if (shouldReturnSuccess) {
            Result.success(items)
        } else {
            Result.failure(Exception())
        }
    }

    fun setItems(items: List<Character>) {
        this.items.clear()
        this.items.addAll(items)
    }

    fun setShouldReturnSuccess(isSuccess: Boolean) {
        shouldReturnSuccess = isSuccess
    }
}