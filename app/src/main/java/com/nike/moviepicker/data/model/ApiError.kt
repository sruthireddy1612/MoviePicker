package com.nike.moviepicker.data.model

data class ApiError(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)