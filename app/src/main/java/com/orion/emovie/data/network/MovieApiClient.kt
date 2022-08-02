package com.orion.emovie.data.network

import androidx.annotation.CallSuper
import com.google.gson.JsonObject
import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.data.model.MovieResponseModel
import com.orion.emovie.data.model.TrailersModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {

    @GET("movie/{movie_id}")
    @CallSuper
    suspend fun getMovieDetails(@Path("movie_id")id: Int): Response<MovieDetailsModel>

    @GET("movie/upcoming")
    @CallSuper
    suspend fun getUpcoming(): Response<MovieResponseModel>

    @GET("movie/top_rated")
    @CallSuper
    suspend fun getTopRated(): Response<MovieResponseModel>

    @GET("movie/{tv_id}/videos")
    @CallSuper
    suspend fun getTrailerDetails(@Path("tv_id") id: Int): Response<TrailersModel>


}