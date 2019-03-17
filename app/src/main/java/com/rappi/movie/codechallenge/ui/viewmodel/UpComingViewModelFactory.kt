package com.rappi.movie.codechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository

class UpComingViewModelFactory(
    private val repository: TheMovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpcomingViewModel(
            repository
        ) as T
    }
}