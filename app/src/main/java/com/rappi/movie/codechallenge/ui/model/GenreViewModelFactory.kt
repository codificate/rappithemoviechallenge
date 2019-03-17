package com.rappi.movie.codechallenge.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class GenreViewModelFactory(
    private val repository: TheMovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenreViewModel(
            repository
        ) as T
    }
}