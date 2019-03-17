package com.rappi.movie.codechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class ConfigurationViewModel(
    private val repository: TheMovieRepository
): ViewModel() {
    val fecthConfiguration by lazyDeferred {
        repository.getConfiguration()
    }
}