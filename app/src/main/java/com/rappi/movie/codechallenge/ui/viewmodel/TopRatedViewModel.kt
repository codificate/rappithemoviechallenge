package com.rappi.movie.codechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class TopRatedViewModel(
    private val repository: TheMovieRepository
): ViewModel() {
    val fetchTopRatedMovies by lazyDeferred {
        repository.getTopRatedMovies()
    }
}