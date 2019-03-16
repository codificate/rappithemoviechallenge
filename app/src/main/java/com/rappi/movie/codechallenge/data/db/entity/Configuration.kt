package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "configurationDao")
data class Configuration(
    val change_keys: List<String>,
    @Embedded
    val images: Images
)