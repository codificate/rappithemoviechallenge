package com.rappi.movie.codechallenge.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class ConfigurationViewModelFactory(
    private val repository: TheMovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigurationViewModel(
            repository
        ) as T
    }
}