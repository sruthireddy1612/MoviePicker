package com.nike.moviepicker.data.source

import com.nike.moviepicker.data.model.MovieList
import com.nike.moviepicker.data.model.Output

interface MoviesRepository {

    suspend fun discoverMovies(): Output<MovieList>
}