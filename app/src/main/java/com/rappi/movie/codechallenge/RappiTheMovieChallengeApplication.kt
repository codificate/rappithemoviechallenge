package com.rappi.movie.codechallenge

import android.app.Application
import com.rappi.movie.codechallenge.data.db.TheMovieDatabase
import com.rappi.movie.codechallenge.data.network.TheMovieApiService
import com.rappi.movie.codechallenge.data.network.TheMovieDataSource
import com.rappi.movie.codechallenge.data.network.TheMovieDataSourceImpl
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository
import com.rappi.movie.codechallenge.data.repository.TheMovieRepositoryImpl
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptor
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptorImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class RappiTheMovieChallengeApplication : Application(), KodeinAware {
    override val kodein= Kodein.lazy{
        import(androidModule(this@RappiTheMovieChallengeApplication))
        bind() from singleton { TheMovieDatabase(instance()) }
        bind() from singleton { instance<TheMovieDatabase>().genreDao() }
        bind() from singleton { instance<TheMovieDatabase>().movieDao() }
        bind() from singleton { instance<TheMovieDatabase>().popularDao() }
        bind() from singleton { instance<TheMovieDatabase>().topRatedDao() }
        bind() from singleton { instance<TheMovieDatabase>().upcomingDao() }
        bind() from singleton { instance<TheMovieDatabase>().configurationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { TheMovieApiService(instance()) }
        bind<TheMovieDataSource>() with singleton { TheMovieDataSourceImpl(instance()) }
        bind<TheMovieRepository>() with singleton {TheMovieRepositoryImpl(instance(), instance(), instance(),instance(), instance(), instance(), instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}