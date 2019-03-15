package com.rappi.movie.codechallenge.data.repository

import androidx.lifecycle.LiveData
import com.rappi.movie.codechallenge.data.db.entity.Genre
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.network.response.Configuration
import com.rappi.movie.codechallenge.data.network.response.MovieDetail

interface TheMovieRepository {

    suspend fun getConfiguration(): LiveData<Configuration>

    suspend fun getMovieDetail(id: Int): LiveData<MovieDetail>

    suspend fun getDiscoverMovies(): LiveData<List<Movie>>

    suspend fun getPopularMovies(): LiveData<List<Movie>>

    suspend fun getTopRatedMovies(): LiveData<List<Movie>>

    suspend fun getUpcomingMovies(): LiveData<List<Movie>>

    suspend fun getGenres(): LiveData<List<Genre>>
}