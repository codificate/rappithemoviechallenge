package com.rappi.movie.codechallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rappi.movie.codechallenge.R
import com.rappi.movie.codechallenge.data.db.entity.Images
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.ui.viewholder.MovieItemViewHolder

class MoviesAdapter(val images: Images):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_movie_item,
                    parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieItemViewHolder = holder as MovieItemViewHolder
        movieItemViewHolder.bind(movies[position], images)
    }

    fun setMovies(movies: List<Movie>){
        this.movies = movies
        this.notifyDataSetChanged()
    }
}