package com.rappi.movie.codechallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rappi.movie.codechallenge.common.converter.*
import com.rappi.movie.codechallenge.data.db.dao.*
import com.rappi.movie.codechallenge.data.db.entity.*

@Database(entities = [Genre::class, Configuration::class, Discover::class,
    Movie::class, Popular::class, TopRated::class, Upcoming::class],
    version = 1, exportSchema = false)
@TypeConverters(
    GenreConverter::class, ListStringConverter::class,
    MovieConverter::class, ProductionCompanyConverter::class,
    ProductionCountryConverter::class, SpokenLanguageConverter::class)
abstract class TheMovieDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun popularDao(): PopularDao
    abstract fun topRatedDao(): TopRatedDao
    abstract fun upcomingDao(): UpcomingDao
    abstract fun discoverDao(): DiscoverDao
    abstract fun configurationDao(): ConfigurationDao

    companion object {
        @Volatile private var instance: TheMovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TheMovieDatabase::class.java, "TheMovieChallenge.db")
                .build()
    }
}