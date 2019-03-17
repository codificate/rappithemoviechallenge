package com.rappi.movie.codechallenge.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rappi.movie.codechallenge.BuildConfig
import com.rappi.movie.codechallenge.data.network.response.Configuration
import com.rappi.movie.codechallenge.data.network.response.GenreResponse
import com.rappi.movie.codechallenge.data.network.response.MovieDetail
import com.rappi.movie.codechallenge.data.network.response.ResponseMovies
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApiService {

    @GET("movie/{id}")
    fun movie(@Path("id") id: Int): Deferred<MovieDetail>

    @GET("movie/popular")
    fun popularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("movie/top_rated")
    fun topRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("discover/movie")
    fun movies(
        @Query("page") page: Int = 1,
        @Query("include_video") include_video: Boolean = false,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("language") language: String = "en-US"): Deferred<ResponseMovies>

    @GET("configuration")
    fun configuration(): Deferred<Configuration>

    @GET("genre/movie/list")
    fun genres(@Query("language") language: String = "en-US"): Deferred<GenreResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): TheMovieApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieApiService::class.java)
        }
    }
}