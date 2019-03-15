package com.rappi.movie.codechallenge.data.repository

import androidx.lifecycle.LiveData
import com.rappi.movie.codechallenge.data.db.*
import com.rappi.movie.codechallenge.data.db.entity.Genre
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.network.TheMovieDataSource
import com.rappi.movie.codechallenge.data.network.response.Configuration
import com.rappi.movie.codechallenge.data.network.response.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TheMovieRepositoryImpl(
    private val genreDao: GenreDao,
    private val movieDao: MovieDao,
    private val popularDao: PopularDao,
    private val topRatedDao: TopRatedDao,
    private val upcomingDao: UpcomingDao,
    private val dataSource: TheMovieDataSource
) : TheMovieRepository {

    init {
        dataSource.apply {
            downloadedConfiguration.observeForever {  }

            downloadedGenres.observeForever { genreResponse ->
                persistGenres(genreResponse.genres.map { Genre(it.id, it.name) })
            }
        }
    }

    private suspend fun initGenres(){
        val genre = genreDao.getFirst()
        if (genre.value?.id == null){ dataSource.fetchGenres() }
        return
    }

    private fun persistGenres(genres: List<Genre>) {
        GlobalScope.launch(Dispatchers.IO) {
            genreDao.insert(genres)
        }
    }

    override suspend fun getGenres(): LiveData<List<Genre>> {
        initGenres()
        return withContext(Dispatchers.IO) {
            return@withContext genreDao.getAll()
        }
    }

    private suspend fun

    override suspend fun getConfiguration(): LiveData<Configuration> {
        return withContext(Dispatchers.IO) {
            return@withContext
        }
    }

    override suspend fun getMovieDetail(id: Int): LiveData<MovieDetail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDiscoverMovies(): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPopularMovies(): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTopRatedMovies(): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}