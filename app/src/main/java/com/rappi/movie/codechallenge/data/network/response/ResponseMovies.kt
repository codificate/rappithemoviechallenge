package com.rappi.movie.codechallenge.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)