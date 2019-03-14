package com.rappi.movie.codechallenge.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rappi.movie.codechallenge.data.db.entity.Genre

class GenreConverter {
    var gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<Genre> {
        if (data == null) {
            return kotlin.collections.emptyList()
        }

        val listType = object : TypeToken<List<Genre>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: List<Genre>): String {
        return gson.toJson(someObjects)
    }
}