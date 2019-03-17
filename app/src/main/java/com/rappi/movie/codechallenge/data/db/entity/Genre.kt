package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var name: String? = null
)