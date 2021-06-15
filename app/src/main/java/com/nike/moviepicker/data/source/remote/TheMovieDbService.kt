package com.nike.moviepicker.data.source.remote

import com.nike.moviepicker.BuildConfig
import com.nike.moviepicker.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TheMovieDbService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("include_adult") include_adult: Boolean = false,
        @Query("include_video") include_video: Boolean = false,
        @Query("page") page: Int = 1,
        @Query("with_watch_monetization_types") with_watch_monetization_types: String = "flatrate"
    ): Response<MovieList>
}