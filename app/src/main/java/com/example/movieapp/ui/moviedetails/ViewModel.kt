package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.repository.MovieDetailsRepository
import com.example.movieapp.util.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel (private val movieRepository : MovieDetailsRepository, movieId: Int)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    /**dispose api to prevent memory leak**/
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}