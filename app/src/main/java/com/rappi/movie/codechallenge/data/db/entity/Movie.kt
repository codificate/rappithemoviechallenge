package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.GenreConverter

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int? = null,
    @TypeConverters(GenreConverter::class)
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val imdb_id: String? = null,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int

)