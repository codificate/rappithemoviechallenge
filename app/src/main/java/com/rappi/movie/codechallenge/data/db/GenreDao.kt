package com.rappi.movie.codechallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genreList: List<Genre>)

    @Query("select * from genre")
    fun getAll(): LiveData<List<Genre>>

    @Query("select * from genre limit 1")
    fun getFirst(): LiveData<Genre>
}