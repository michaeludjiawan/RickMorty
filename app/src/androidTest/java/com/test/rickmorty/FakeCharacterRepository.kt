package com.test.rickmorty

import com.test.rickmorty.data.model.Character
import com.test.rickmorty.data.repository.CharacterRepository

class FakeCharacterRepository : CharacterRepository {

    private var items = ArrayList<Character>()
    private var isReturnError = false

    override suspend fun getAllCharacters(page: Int): Result<List<Character>> {
        return if (!isReturnError) {
            Result.success(items)
        } else {
            Result.failure(Exception())
        }
    }

    fun setItems(items: List<Character>) {
        this.items.clear()
        this.items.addAll(items)
    }

    fun setReturnError(returnError: Boolean) {
        isReturnError = returnError
    }
}