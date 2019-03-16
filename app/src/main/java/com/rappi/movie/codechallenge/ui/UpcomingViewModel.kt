package com.rappi.movie.codechallenge.ui

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class UpcomingViewModel(
    private val repository: TheMovieRepository
): ViewModel() {
    val fetchUpcomingMovies by lazyDeferred {
        repository.getUpcomingMovies()
    }
}