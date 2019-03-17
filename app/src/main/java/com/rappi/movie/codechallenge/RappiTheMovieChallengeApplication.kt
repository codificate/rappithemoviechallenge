package com.rappi.movie.codechallenge

import android.app.Application
import com.rappi.movie.codechallenge.data.db.TheMovieDatabase
import com.rappi.movie.codechallenge.data.network.TheMovieApiService
import com.rappi.movie.codechallenge.data.network.TheMovieDataSource
import com.rappi.movie.codechallenge.data.network.TheMovieDataSourceImpl
import com.rappi.movie.codechallenge.data.repository.TheMovieRepository
import com.rappi.movie.codechallenge.data.repository.TheMovieRepositoryImpl
import com.rappi.movie.codechallenge.ui.model.*
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptor
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptorImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
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
        bind() from singleton { instance<TheMovieDatabase>().discoverDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { TheMovieApiService(instance()) }
        bind<TheMovieDataSource>() with singleton { TheMovieDataSourceImpl(instance()) }
        bind<TheMovieRepository>() with singleton {TheMovieRepositoryImpl(instance(), instance(), instance(),instance(), instance(), instance(), instance(), instance()) }
        bind() from provider { ConfigurationViewModelFactory(instance()) }
        bind() from provider { GenreViewModelFactory(instance()) }
        bind() from provider { DiscoverViewModelFactory(instance()) }
        bind() from provider { PopularViewModelFactory(instance()) }
        bind() from provider { TopRatedViewModeFactory(instance()) }
        bind() from provider { UpComingViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}