package com.rappi.movie.codechallenge.ui

import com.rappi.movie.codechallenge.common.lazyDeferred
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class ConfigurationViewModel(
    private val repository: TheMovieRepository
) {
    val fecthConfiguration by lazyDeferred {
        repository.getConfiguration()
    }
}