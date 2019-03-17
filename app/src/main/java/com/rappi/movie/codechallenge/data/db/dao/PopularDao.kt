package com.rappi.movie.codechallenge.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.db.entity.Popular

@Dao
interface PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(popularList: List<Popular>)

    @Query("select m.* from movie m inner join popular p on p.movie_id=m.Id")
    fun getAll(): LiveData<List<Movie>>

    @Query("select * from popular limit 1")
    fun getFirst(): LiveData<Popular>
}