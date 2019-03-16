package com.rappi.movie.codechallenge.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rappi.movie.codechallenge.common.NoConnectivityException
import com.rappi.movie.codechallenge.data.network.response.Configuration
import com.rappi.movie.codechallenge.data.network.response.GenreResponse
import com.rappi.movie.codechallenge.data.network.response.MovieDetail
import com.rappi.movie.codechallenge.data.network.response.ResponseMovies

class TheMovieDataSourceImpl(
    private val theMovieApiService: TheMovieApiService
): TheMovieDataSource {

    private val _downloadedGenres = MutableLiveData<GenreResponse>()
    override val downloadedGenres: LiveData<GenreResponse>
        get() = _downloadedGenres

    private val _downloadedConfiguration = MutableLiveData<Configuration>()
    override val downloadedConfiguration: LiveData<Configuration>
        get() = _downloadedConfiguration

    private val _downloadedPopularMovies = MutableLiveData<ResponseMovies>()
    override val downloadedPopularMovies: LiveData<ResponseMovies>
        get() = _downloadedPopularMovies

    private val _downloadedTopRatedMovies = MutableLiveData<ResponseMovies>()
    override val downloadedTopRatedMovies: LiveData<ResponseMovies>
        get() = _downloadedTopRatedMovies

    private val _downloadedUpcomingMovies = MutableLiveData<ResponseMovies>()
    override val downloadedUpcomingMovies: LiveData<ResponseMovies>
        get() = _downloadedUpcomingMovies

    private val _downloadedDiscoverMovies = MutableLiveData<ResponseMovies>()
    override val downloadedDiscoverMovies: LiveData<ResponseMovies>
        get() = _downloadedDiscoverMovies

    private val _fetchDetailMovie= MutableLiveData<MovieDetail>()
    private val fetchDetailMovie: LiveData<MovieDetail>
        get() = _fetchDetailMovie

    override suspend fun fetchGenres() {
        try {
            val fecthedGenres = theMovieApiService.genres().await()
            _downloadedGenres.postValue(fecthedGenres)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchConfiguration() {
        try {
            val fetchedConfiguration = theMovieApiService.configuration().await()
            _downloadedConfiguration.postValue(fetchedConfiguration)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchPopularMovies() {
        try {
            val fetchedPopularMovies = theMovieApiService.popularMovies().await()
            _downloadedPopularMovies.postValue(fetchedPopularMovies)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchUpcomingMovies() {
        try {
            val fetchedUpcomingMovies = theMovieApiService.upcomingMovies().await()
            _downloadedUpcomingMovies.postValue(fetchedUpcomingMovies)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchTopRatedMovies() {
        try {
            val fetchedTopRatedMovies = theMovieApiService.topRatedMovies().await()
            _downloadedTopRatedMovies.postValue(fetchedTopRatedMovies)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchDiscoverMovies() {
        try {
            val fetchedDiscoverMovies = theMovieApiService.movies().await()
            _downloadedDiscoverMovies.postValue(fetchedDiscoverMovies)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchMovieDetail(id: Int): LiveData<MovieDetail>? {

        try {
            val fetchDetailMovie = theMovieApiService.movie(id).await()
            _fetchDetailMovie.postValue(fetchDetailMovie)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }

        return fetchDetailMovie
    }
}