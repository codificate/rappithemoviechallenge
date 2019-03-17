package com.rappi.movie.codechallenge.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.db.entity.Upcoming

@Dao
interface UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(upcomingList: List<Upcoming>)

    @Query("select m.* from movie m inner join upcoming uc on uc.movie_id=m.Id")
    fun getAll(): LiveData<List<Movie>>

    @Query("select * from upcoming limit 1")
    fun getFirst(): LiveData<Upcoming>
}