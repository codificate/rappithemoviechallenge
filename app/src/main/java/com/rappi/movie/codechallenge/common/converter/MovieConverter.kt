package com.rappi.movie.codechallenge.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rappi.movie.codechallenge.data.db.entity.Movie

class MovieConverter {
    var gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<Movie> {
        if (data == null) {
            return kotlin.collections.emptyList()
        }

        val listType = object : TypeToken<List<Movie>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: List<Movie>): String {
        return gson.toJson(someObjects)
    }
}