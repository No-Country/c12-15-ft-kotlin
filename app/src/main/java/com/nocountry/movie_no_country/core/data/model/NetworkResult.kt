package com.nocountry.movie_no_country.core.data.model

import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}