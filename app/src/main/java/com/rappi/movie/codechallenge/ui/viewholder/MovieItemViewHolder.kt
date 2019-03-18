package com.rappi.movie.codechallenge.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rappi.movie.codechallenge.common.glide.GlideApp
import com.rappi.movie.codechallenge.data.db.entity.Images
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.ui.helper.MoviePicturePath
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(movie: Movie, images: Images) {
        showMoviePicture(movie, images)
        setMovieTitile(movie.title)
        setMovieRate(movie.vote_count.toString())
    }

    private fun setMovieTitile(movieTitle: String){
        itemView.movieTitle.text = movieTitle
    }

    private fun setMovieRate(movieVotes: String){
        itemView.movieRate.text = movieVotes
    }

    private fun showMoviePicture(movie: Movie, images: Images){
        GlideApp.with(itemView.context)
            .load(MoviePicturePath.build(movie, images))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(itemView.imageMovie)
    }
}