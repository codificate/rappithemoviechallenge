package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toprated")
data class TopRated(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var movie_id: Int
)