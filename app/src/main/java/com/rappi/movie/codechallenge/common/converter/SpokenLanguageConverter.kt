package com.rappi.movie.codechallenge.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rappi.movie.codechallenge.data.db.entity.SpokenLanguage

class SpokenLanguageConverter {
    var gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<SpokenLanguage> {
        if (data == null) {
            return kotlin.collections.emptyList()
        }

        val listType = object : TypeToken<List<SpokenLanguage>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: List<SpokenLanguage>): String {
        return gson.toJson(someObjects)
    }
}