package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.MovieConverter
import com.rappi.movie.codechallenge.data.network.response.Movie

data class Upcoming(
    @TypeConverters(MovieConverter::class)
    val movies: List<Movie> )