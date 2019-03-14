package com.rappi.movie.codechallenge.data.network.response

data class ResponseMovies(
    val page: Int,
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)