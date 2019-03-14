package com.rappi.movie.codechallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rappi.movie.codechallenge.data.db.entity.Genre
import com.rappi.movie.codechallenge.data.db.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulk(movieList: List<Movie>)

    @Query("select * from movie where id = :id")
    fun getDetail(id: Int): LiveData<Movie>

    @Query("select * from movie where genres in (:genreList)")
    fun getByGenre(genreList: List<Genre>)


}