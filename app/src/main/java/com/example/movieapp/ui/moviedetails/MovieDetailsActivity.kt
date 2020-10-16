package com.example.movieapp.ui.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.network.MovieDBInterface
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.network.MovieNetworkClient
import com.example.movieapp.network.POSTER_BASE_URL
import com.example.movieapp.util.NetworkState
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.text.NumberFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId: Int = intent.getIntExtra("id",1)

        /**initialize variables**/
        val apiService : MovieDBInterface = MovieNetworkClient.getClient()

        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        /**observe data result**/
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        /**observe network state**/
        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })
    }

    /**display result**/
    fun bindUI( it: MovieDetails){
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }


    /**viewModel factory**/
    private fun getViewModel(movieId:Int): SingleMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}

//api request--https://api.themoviedb.org/3/movie/550?api_key=9d6c64427ab395f6f6eb05b250e4850f
//api-key--9d6c64427ab395f6f6eb05b250e4850f
//api RAT--eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZDZjNjQ0MjdhYjM5NWY2ZjZlYjA1YjI1MGU0ODUwZiIsInN1YiI6IjVmODllNDVmMzk1NDlhMDAzOGJhNzRiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OKiIHrVh5rttovzGGicbqXazNm6a-dMn5vWns92WhM0