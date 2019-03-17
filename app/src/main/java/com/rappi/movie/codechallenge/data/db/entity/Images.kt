package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.ListStringConverter

data class Images(
    @TypeConverters(ListStringConverter::class)
    val backdrop_sizes: List<String>,
    val base_url: String,
    @TypeConverters(ListStringConverter::class)
    val logo_sizes: List<String>,
    @TypeConverters(ListStringConverter::class)
    val poster_sizes: List<String>,
    @TypeConverters(ListStringConverter::class)
    val profile_sizes: List<String>,
    val secure_base_url: String,
    @TypeConverters(ListStringConverter::class)
    val still_sizes: List<String>
)