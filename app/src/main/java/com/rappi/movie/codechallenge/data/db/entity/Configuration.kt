package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.ListStringConverter

@Entity(tableName = "configuration")
data class Configuration(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @TypeConverters(ListStringConverter::class)
    var change_keys: List<String>,
    @Embedded
    var images: Images
)