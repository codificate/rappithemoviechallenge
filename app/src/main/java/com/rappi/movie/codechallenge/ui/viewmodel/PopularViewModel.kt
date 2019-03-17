package com.rappi.movie.codechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class PopularViewModel(
    private val repository: TheMovieRepository
): ViewModel()  {
    val fetchPopularMovies by lazyDeferred {
        repository.getPopularMovies()
    }
}