package com.rappi.movie.codechallenge.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "popular",foreignKeys = [ForeignKey(
    entity = Movie::class, parentColumns = ["id"],
    childColumns = ["movie_id"], onDelete = CASCADE)])
data class Popular(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "movie_id") var movie_id: Int
)