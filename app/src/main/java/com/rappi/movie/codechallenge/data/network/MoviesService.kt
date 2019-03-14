package com.rappi.movie.codechallenge.data.network

import com.rappi.movie.codechallenge.data.network.response.MovieDetail
import com.rappi.movie.codechallenge.data.network.response.ResponseMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("movie/{id}")
    fun movie(@Path("id") id: Int): Deferred<MovieDetail>

    @GET("movie/popular")
    fun popularMovies(): Deferred<ResponseMovies>

    @GET("movie/upcoming")
    fun upcomingMovies(): Deferred<ResponseMovies>

    @GET("movie/top_rated")
    fun topRatedMovies(): Deferred<ResponseMovies>

    @GET("discover/movie")
    fun movies(): Deferred<ResponseMovies>
}