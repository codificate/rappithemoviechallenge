package com.rappi.movie.codechallenge.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Discover
import com.rappi.movie.codechallenge.data.db.entity.Movie

@Dao
interface DiscoverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(discoverList: List<Discover>)

    @Query("select m.* from movie m inner join discover d on d.movie_id=m.Id")
    fun getAll(): LiveData<List<Movie>>

    @Query("select * from discover limit 1")
    fun getFirst(): LiveData<Discover>
}