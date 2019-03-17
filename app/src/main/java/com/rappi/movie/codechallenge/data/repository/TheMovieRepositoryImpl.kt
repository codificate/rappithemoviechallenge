package com.rappi.movie.codechallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rappi.movie.codechallenge.data.db.dao.*
import com.rappi.movie.codechallenge.data.db.entity.*
import com.rappi.movie.codechallenge.data.db.entity.Configuration
import com.rappi.movie.codechallenge.data.db.entity.Genre
import com.rappi.movie.codechallenge.data.db.entity.Images
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.data.network.TheMovieDataSource
import com.rappi.movie.codechallenge.data.network.response.*
import com.rappi.movie.codechallenge.data.network.response.ProductionCompany
import com.rappi.movie.codechallenge.data.network.response.ProductionCountry
import com.rappi.movie.codechallenge.data.network.response.SpokenLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TheMovieRepositoryImpl(
    private val genreDao: GenreDao,
    private val movieDao: MovieDao,
    private val popularDao: PopularDao,
    private val discoverDao: DiscoverDao,
    private val topRatedDao: TopRatedDao,
    private val upcomingDao: UpcomingDao,
    private val configurationDao: ConfigurationDao,
    private val theMovieDataSource: TheMovieDataSource
) : TheMovieRepository {

    private var genres: List<Genre> = listOf()
    private var genresByMovie: HashMap<Int, List<Genre>> = HashMap()

    init {
        theMovieDataSource.apply {
            downloadedConfiguration.observeForever { configurationResponse ->
                persistConfiguration( configurationResponse.toEntity())
            }

            downloadedGenres.observeForever { genreResponse ->
                persistGenres(genreResponse.genres.map { Genre(it.id, it.name) })
            }

            downloadedDiscoverMovies.observeForever { discoverResponse ->
                matchGenresToMovies(discoverResponse)
                persistDiscover(discoverResponse.toDiscoverEntity())
            }

            downloadedPopularMovies.observeForever { popularMoviesResponse ->
                matchGenresToMovies(popularMoviesResponse)
                persistPopular(popularMoviesResponse.toPopularEntity())
            }

            downloadedTopRatedMovies.observeForever { topRatedMoviesResponse ->
                matchGenresToMovies(topRatedMoviesResponse)
                persistTopRated(topRatedMoviesResponse.toTopRatedEntity())
            }

            downloadedUpcomingMovies.observeForever { upcomingMoviesResponse ->
                matchGenresToMovies(upcomingMoviesResponse)
                persistUpcoming(upcomingMoviesResponse.toUpcomingEntity())
            }
        }
    }

    override suspend fun getConfiguration(): LiveData<Configuration> {
        initConfiguration()
        return withContext(Dispatchers.IO) {
            return@withContext configurationDao.getAll()
        }
    }

    override suspend fun getGenres(): LiveData<List<Genre>> {
        initGenres()
        return withContext(Dispatchers.IO) {
            return@withContext genreDao.getAll()
        }
    }

    override suspend fun getDiscoverMovies(): LiveData<List<Movie>> {
        initDiscoverMovies()
        return withContext(Dispatchers.IO) {
            return@withContext discoverDao.getAll()
        }
    }

    override suspend fun getPopularMovies(): LiveData<List<Movie>> {
        initPopularMovies()
        return withContext(Dispatchers.IO){
            return@withContext popularDao.getAll()
        }
    }

    override suspend fun getTopRatedMovies(): LiveData<List<Movie>> {
        initTopRatedMovies()
        return withContext(Dispatchers.IO) {
            return@withContext topRatedDao.getAll()
        }
    }

    override suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        initUpcomingMovies()
        return withContext(Dispatchers.IO){
            return@withContext upcomingDao.getAll()
        }
    }

    override suspend fun getMovieDetail(id: Int): LiveData<MovieDetail>? {
        return withContext(Dispatchers.IO){
            return@withContext processMovieDetail(id)
        }
    }

    override suspend fun search(movieName: String): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private suspend fun initGenres(){
        val genre = genreDao.getFirst()
        if (genre.value?.id == null){ theMovieDataSource.fetchGenres() }
        return
    }

    private suspend fun initConfiguration(){
        val configuration = configurationDao.getFirst()
        if (configuration.value?.images == null){ theMovieDataSource.fetchConfiguration() }
        return
    }

    private suspend fun initDiscoverMovies(){
        val discover = discoverDao.getFirst()
        if (discover.value?.id == null){ theMovieDataSource.fetchDiscoverMovies() }
        return
    }

    private suspend fun initPopularMovies() {
        val popular = popularDao.getFirst()
        if (popular.value?.id == null){ theMovieDataSource.fetchPopularMovies() }
        return
    }

    private suspend fun initTopRatedMovies() {
        val topRated = topRatedDao.getFirst()
        if (topRated.value?.id == null){ theMovieDataSource.fetchTopRatedMovies() }
        return
    }

    private suspend fun initUpcomingMovies() {
        val upcoming = upcomingDao.getFirst()
        if (upcoming.value?.id == null){ theMovieDataSource.fetchUpcomingMovies() }
        return
    }

    private fun persistGenres(genres: List<Genre>) {
        GlobalScope.launch(Dispatchers.IO) {
            genreDao.insert(genres)
            this@TheMovieRepositoryImpl.genres = genres
        }
    }

    private fun persistConfiguration(configuration: Configuration){
        GlobalScope.launch(Dispatchers.IO) {
            configurationDao.insert(configuration)
        }
    }

    private fun persistMovies(movieList: List<Movie>){
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.bulk(movieList)
        }
    }

    private fun persistDiscover(discoverList: List<Discover>){
        GlobalScope.launch(Dispatchers.IO){
            discoverDao.insert(discoverList)
        }
    }

    private fun persistPopular(popularList: List<Popular>){
        GlobalScope.launch(Dispatchers.IO){
            popularDao.insert(popularList)
        }
    }

    private fun persistTopRated(topRatedList: List<TopRated>){
        GlobalScope.launch(Dispatchers.IO){
            topRatedDao.insert(topRatedList)
        }
    }

    private fun persistUpcoming(upComingList: List<Upcoming>){
        GlobalScope.launch(Dispatchers.IO){
            upcomingDao.insert(upComingList)
        }
    }

    private fun processMovieDetail(id: Int): LiveData<MovieDetail>? {
        var movie:LiveData<MovieDetail>?
        movie = toMovieDetail(movieDao.getDetail(id))
        if (movie.value?.id==null){
            GlobalScope.launch(Dispatchers.IO) {
                movie = theMovieDataSource.fetchMovieDetail(id)
                if (movie!!.value?.id != null){
                    movieDao.upsert(toMovieEntity(movie!!.value))
                }
            }
        }
        return movie
    }

    private fun ResponseMovies.toUpcomingEntity(): List<Upcoming>{
        return this.movies.map { Upcoming(null, it.id) }
    }

    private fun ResponseMovies.toTopRatedEntity(): List<TopRated>{
        return this.movies.map { TopRated(null, it.id) }
    }

    private fun ResponseMovies.toPopularEntity(): List<Popular>{
        return this.movies.map { Popular(null, it.id) }
    }

    private fun ResponseMovies.toDiscoverEntity(): List<Discover>{
        return this.movies.map { Discover(null, it.id) }
    }

    private fun matchGenresToMovies(responseMovies: ResponseMovies){

        responseMovies.movies.forEach( action = { movie ->
            genresByMovie[movie.id] = movie.genre_ids.toGenresEntityList(genres)
        })

        persistMovies(responseMovies.toMoviesEntity())
    }

    private fun List<Int>.toGenresEntityList(genres: List<Genre>): List<Genre>{
        return this.map { id -> genres.first{ genre -> genre.id == id } }
    }

    private fun ResponseMovies.toMoviesEntity(): List<Movie>{
        return this.movies.map {
            Movie(id= it.id, vote_count = it.vote_count, video = it.video, vote_average = it.vote_average,
                title = it.title, popularity = it.popularity, poster_path = it.poster_path,
                original_language = it.original_language, original_title = it.original_title,
                genres = this@TheMovieRepositoryImpl.genresByMovie[it.id], backdrop_path = it.backdrop_path,
                adult = it.adult, overview = it.overview, release_date = it.release_date)
        }
    }

    private fun com.rappi.movie.codechallenge.data.network.response.Configuration.toEntity(): Configuration {
        return Configuration(null,
            this.change_keys,
            Images(
                this.images.backdrop_sizes,
                this.images.base_url,
                this.images.logo_sizes,
                this.images.poster_sizes,
                this.images.profile_sizes,
                this.images.secure_base_url,
                this.images.still_sizes) )
    }

    private fun toMovieEntity(movieDetail: MovieDetail?): Movie{

        var movie: Movie?

        val genres = movieDetail!!.genres.map { Genre(it.id, it.name) }

        movie = Movie(movieDetail.id,movieDetail.adult, movieDetail.backdrop_path,
            movieDetail.budget, genres, movieDetail.homepage, movieDetail.imdb_id, movieDetail.original_language,
            movieDetail.original_title, movieDetail.overview, movieDetail.popularity, movieDetail.poster_path,
            movieDetail.release_date, movieDetail.revenue, movieDetail.runtime,
            movieDetail.status, movieDetail.tagline, movieDetail.original_title, movieDetail.video,
            movieDetail.vote_average, movieDetail.vote_count)

        return movie
    }

    private fun toMovieDetail(movie: Movie): LiveData<MovieDetail>{

        val mutableLiveData = MutableLiveData<MovieDetail>()

        val genresList = movie.genres!!.map { com.rappi.movie.codechallenge.data.network.response.Genre(it.id, it.name!!) }

        val movieDetail = MovieDetail(
            movie.adult, movie.backdrop_path,
            null, movie.budget!!, genresList,
            movie.homepage!!, movie.id!!, movie.imdb_id!!, movie.original_language, movie.original_title, movie.overview,
            movie.popularity, movie.poster_path, movie.release_date,
            movie.revenue!!, movie.runtime!!, movie.status!!, movie.tagline!!, movie.title,
            movie.video, movie.vote_average, movie.vote_count)

        mutableLiveData.postValue(movieDetail)

        return mutableLiveData
    }
}