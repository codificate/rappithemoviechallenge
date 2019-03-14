package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.GenreConverter
import com.rappi.movie.codechallenge.common.converter.ProductionCompanyConverter
import com.rappi.movie.codechallenge.common.converter.ProductionCountryConverter
import com.rappi.movie.codechallenge.common.converter.SpokenLanguageConverter

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    @Embedded
    val belongs_to_collection: BelongsToCollection? = null,
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
    @TypeConverters(ProductionCompanyConverter::class)
    val production_companies: List<ProductionCompany>? = null,
    @TypeConverters(ProductionCountryConverter::class)
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String,
    val revenue: Int? = null,
    val runtime: Int? = null,
    @TypeConverters(SpokenLanguageConverter::class)
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int

)