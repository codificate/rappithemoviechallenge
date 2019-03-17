package com.rappi.movie.codechallenge.ui.model

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class DiscoverViewModel(
    private val repository: TheMovieRepository
) : ViewModel() {
    val fetchDiscoverMovies by lazyDeferred {
        repository.getDiscoverMovies()
    }
}