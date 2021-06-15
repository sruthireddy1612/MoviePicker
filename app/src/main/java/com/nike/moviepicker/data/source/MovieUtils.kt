package com.nike.moviepicker.data.source

import com.nike.moviepicker.data.model.ApiError
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import retrofit2.Response

object MovieUtils {

    @Throws(JsonParseException::class)
    fun parseError(response: Response<*>): ApiError? {
        val gson = GsonBuilder().create()
        val errorBody: String? = response.errorBody()?.string()?.trim()
        return gson.fromJson(errorBody, ApiError::class.java)
    }
}