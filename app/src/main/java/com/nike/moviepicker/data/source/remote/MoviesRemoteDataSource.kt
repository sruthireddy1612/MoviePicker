package com.nike.moviepicker.data.source.remote

import com.nike.moviepicker.BuildConfig
import com.nike.moviepicker.data.model.MovieList
import com.nike.moviepicker.data.model.Output
import com.nike.moviepicker.data.source.MovieUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.THEMOVIEDB_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val tmdbService: TheMovieDbService = retrofit.create(TheMovieDbService::class.java)

    suspend fun discoverMovies(): Output<MovieList> {
        val discoverMoviesResponse = tmdbService.discoverMovies()
        return if (discoverMoviesResponse.isSuccessful) {
            val body = discoverMoviesResponse.body()
            if (body != null) {
                Output.Success(body)
            } else {
                Output.Error(
                    ERROR_DISCOVER_MOVIE_LIST_NULL,
                    "Movie List is null",
                    NullPointerException()
                )
            }
        } else {
            val apiError = MovieUtils.parseError(discoverMoviesResponse)
            if (apiError != null) {
                Output.Error(apiError.status_code, apiError.status_message)
            } else {
                Output.Error(
                    ERROR_DISCOVER_MOVIE_PARSE_ERROR,
                    "Error while parsing movie list",
                    NullPointerException()
                )
            }
        }
    }

    companion object {
        const val ERROR_DISCOVER_MOVIE_LIST_NULL = 5101
        const val ERROR_DISCOVER_MOVIE_PARSE_ERROR = 5102
    }

}