package com.rappi.movie.codechallenge.data.network

import com.rappi.movie.codechallenge.data.network.response.Configuration
import com.rappi.movie.codechallenge.data.network.response.GenreResponse
import com.rappi.movie.codechallenge.data.network.response.MovieDetail
import com.rappi.movie.codechallenge.data.network.response.ResponseMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/{id}")
    fun movie(@Path("id") id: Int): Deferred<MovieDetail>

    @GET("movie/popular")
    fun popularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("movie/top_rated")
    fun topRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("discover/movie")
    fun movies(
        @Query("page") page: Int = 1,
        @Query("include_video") include_video: Boolean = false,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("configurationDao")
    fun configuration(): Deferred<Configuration>

    @GET("genre/movie/list")
    fun genres(@Query("language") language: String = "en-US"): Deferred<GenreResponse>
}