package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }
}

//api request--https://api.themoviedb.org/3/movie/550?api_key=9d6c64427ab395f6f6eb05b250e4850f
//api-key--9d6c64427ab395f6f6eb05b250e4850f
//api RAT--eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZDZjNjQ0MjdhYjM5NWY2ZjZlYjA1YjI1MGU0ODUwZiIsInN1YiI6IjVmODllNDVmMzk1NDlhMDAzOGJhNzRiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OKiIHrVh5rttovzGGicbqXazNm6a-dMn5vWns92WhM0