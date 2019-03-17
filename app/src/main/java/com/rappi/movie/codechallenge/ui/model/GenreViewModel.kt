package com.rappi.movie.codechallenge.ui.model

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class GenreViewModel(
private val repository: TheMovieRepository
): ViewModel() {
    val fecthGenres by lazyDeferred {
        repository.getGenres()
    }
}