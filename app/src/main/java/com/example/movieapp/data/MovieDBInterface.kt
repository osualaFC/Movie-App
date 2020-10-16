package com.example.movieapp.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBInterface {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id:Int): Single<MovieDetails>// single--type of observable that emits a single data or an error
}