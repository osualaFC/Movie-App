package com.example.movieapp.network

import com.example.movieapp.data.MovieDetails
import com.example.movieapp.data.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page")page:Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id:Int): Single<MovieDetails>
}
// single--type of observable that emits a single data or an error--it has no onNext or onComplete just onSuccess
//Maybe is similar to Single but this time, it allows your observable the ability to not emit any item at all.
//Completable--This is useful for cases wherein you are only interested if something has executed properly, ignoring output or whatever.