package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular")
data class Popular(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movie_id: Int? = null
)