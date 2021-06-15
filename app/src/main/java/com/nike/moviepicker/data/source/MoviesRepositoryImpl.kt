package com.nike.moviepicker.data.source

import com.nike.moviepicker.data.model.MovieList
import com.nike.moviepicker.data.model.Output
import com.nike.moviepicker.data.source.remote.MoviesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun discoverMovies(): Output<MovieList> {
        return moviesRemoteDataSource.discoverMovies()
    }
}