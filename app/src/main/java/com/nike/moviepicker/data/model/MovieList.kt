package com.nike.moviepicker.data.model

data class MovieList(
    val page: Int = 0,
    val results: List<Movie> = ArrayList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)