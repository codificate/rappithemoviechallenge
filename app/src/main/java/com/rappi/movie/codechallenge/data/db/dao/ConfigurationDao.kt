package com.rappi.movie.codechallenge.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Configuration

interface ConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(configuration: Configuration)

    @Query("select * from configurationDao")
    fun getAll(): LiveData<Configuration>

    @Query("select * from configurationDao limit 1")
    fun getFirst(): LiveData<Configuration>
}