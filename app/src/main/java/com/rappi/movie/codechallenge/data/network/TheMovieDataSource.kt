package com.rappi.movie.codechallenge.data.network

import androidx.lifecycle.LiveData
import com.rappi.movie.codechallenge.data.network.response.*

interface TheMovieDataSource {

    val downloadedConfiguration: LiveData<Configuration>
    val downloadedMovies: LiveData<ResponseMovies>
    val downloadedGenres: LiveData<GenreResponse>

    suspend fun fetchGenres()

    suspend fun fetchConfiguration()

    suspend fun fetchPopularMovies()

    suspend fun fetchUpcomingMovies()

    suspend fun fetchTopRatedMovies()

    suspend fun fetchDiscoverMovies()

    suspend fun fetchMovieDetail(id: Int): LiveData<MovieDetail>?
}