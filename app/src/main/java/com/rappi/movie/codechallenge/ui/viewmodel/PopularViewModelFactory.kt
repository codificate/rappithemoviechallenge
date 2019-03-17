package com.rappi.movie.codechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class PopularViewModelFactory(
    private val repository: TheMovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularViewModel(
            repository
        ) as T
    }
}