package com.rappi.movie.codechallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.db.entity.TopRated

@Dao
interface TopRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(topRatedList: List<TopRated>)

    @Query("select m.* from movie m inner join toprated tr on tr.movie_id=m.Id")
    fun getAll(): LiveData<List<Movie>>

    @Query("select * from toprated limit 1")
    fun getFirst(): LiveData<Movie>
}