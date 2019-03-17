package com.rappi.movie.codechallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rappi.movie.codechallenge.R
import com.rappi.movie.codechallenge.common.glide.GlideApp
import com.rappi.movie.codechallenge.data.db.entity.Images
import com.rappi.movie.codechallenge.data.db.entity.Movie
import com.rappi.movie.codechallenge.ui.adapter.MoviesAdapter
import com.rappi.movie.codechallenge.ui.base.ScopedFragment
import com.rappi.movie.codechallenge.ui.helper.GridItemDecoration
import com.rappi.movie.codechallenge.ui.helper.MoviePosterPath
import com.rappi.movie.codechallenge.ui.viewmodel.*
import kotlinx.android.synthetic.main.content_movies_scrolling.*
import kotlinx.android.synthetic.main.fragment_show_list_movies.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentDiscoverMovies : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private var configurationProcessFinished: Boolean = false
    private var genreProcessFinished: Boolean = false

    private val discoverViewModelFactory: DiscoverViewModelFactory by instance()
    lateinit var discoverViewModel: DiscoverViewModel

    private val configurationViewModelFactory: ConfigurationViewModelFactory by instance()
    lateinit var configurationViewModel: ConfigurationViewModel

    private val genreViewModelFactory: GenreViewModelFactory by instance()
    private lateinit var genreViewModel: GenreViewModel

    lateinit var images: Images

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_list_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configurationViewModel = ViewModelProviders.of(this, configurationViewModelFactory)
            .get(ConfigurationViewModel::class.java)

        genreViewModel = ViewModelProviders.of(this, genreViewModelFactory)
            .get(GenreViewModel::class.java)

        discoverViewModel = ViewModelProviders.of(this, discoverViewModelFactory)
            .get(DiscoverViewModel::class.java)

        setConfigurationData()
    }

    private fun setConfigurationData() = launch {
        configurationViewModel.fecthConfiguration.await().observe(this@FragmentDiscoverMovies, Observer {
                configuration -> images = configuration.images
            configurationProcessFinished = true
            checkTheConfigurationProcessDidFinish()
        })

        genreViewModel.fecthGenres.await().observe(this@FragmentDiscoverMovies, Observer {
            if (it == null) return@Observer

            genreProcessFinished = true
            checkTheConfigurationProcessDidFinish()
        })
    }

    private fun checkTheConfigurationProcessDidFinish(){
        if (configurationProcessFinished && genreProcessFinished){
            bindUi()
        }
    }

    private fun bindUi() = launch {

        val discover = discoverViewModel.fetchDiscoverMovies.await()

        discover.observe(this@FragmentDiscoverMovies, Observer { movies ->
            if (movies==null) return@Observer
            showRecomendedPosterMovie(movies[0])
            showMovies(movies)
        })
    }

    private fun showMovies(movies: List<Movie>){
        recyclerViewMovies.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewMovies.addItemDecoration(GridItemDecoration(10, 2))
        val moviesAdapter = MoviesAdapter(images)
        moviesAdapter.setMovies(movies)
        recyclerViewMovies.adapter = moviesAdapter
    }

    private fun showRecomendedPosterMovie(movie: Movie){
        GlideApp.with(this)
            .load(MoviePosterPath.build(movie, images))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(imagePromotionalMovie)

        imagePromotionalMovie.setOnClickListener(View.OnClickListener {  })
    }
}