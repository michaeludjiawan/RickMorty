package com.test.rickmorty.util

object NetworkUtil {

    /**
     * Wrap API call with exception catcher and encapsulate return data with Result
     */
    suspend fun <T : Any> safeApiCall(call: suspend () -> T): Result<T> {
        return try {
            val response = call.invoke()
            Result.success(response)
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}