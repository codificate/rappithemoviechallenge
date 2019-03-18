package com.rappi.movie.codechallenge.ui.helper

import android.text.TextUtils
import com.rappi.movie.codechallenge.data.db.entity.Images
import com.rappi.movie.codechallenge.data.db.entity.Movie
import java.lang.StringBuilder

object MoviePicturePath {

    private fun getPosterSize(images: Images): String{
        return if (images.poster_sizes.size > 4) images.poster_sizes[4] else "W500"
    }

    private fun getImageMovie(movie: Movie): String{
        return if (!TextUtils.isEmpty(movie.poster_path) ) movie.poster_path else movie.backdrop_path
    }

    fun build(movie: Movie, images: Images): String {
        var url = StringBuilder("")

        if (!TextUtils.isEmpty(images.base_url)){
            url.append(images.base_url)
            if (!images.poster_sizes.isEmpty()){
                url.append(getPosterSize(images))
            }
        }

        return url.append(getImageMovie(movie)).toString()
    }
}