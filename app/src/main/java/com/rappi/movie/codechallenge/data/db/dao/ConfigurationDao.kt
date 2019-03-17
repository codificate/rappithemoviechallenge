package com.rappi.movie.codechallenge.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Configuration

@Dao
interface ConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(configuration: Configuration)

    @Query("select * from configuration")
    fun getAll(): LiveData<Configuration>

    @Query("select * from configuration limit 1")
    fun getFirst(): LiveData<Configuration>
}