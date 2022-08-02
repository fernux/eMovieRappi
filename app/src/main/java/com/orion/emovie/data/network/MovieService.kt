package com.orion.emovie.data.network

import com.orion.emovie.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api:MovieApiClient) {
    var responseList = mutableListOf<MovieModel>()
    suspend fun getMovieUpcoming():MutableList<MovieModel> {

        withContext(Dispatchers.Main) {
            val response = api.getUpcoming()
            if (response.isSuccessful) {
                responseList = response.body()?.movieList ?:arrayListOf()
            }
            else{
                responseList = arrayListOf()
            }
        }
        return responseList

    }
    suspend fun getMovieTopRated():MutableList<MovieModel> {

        withContext(Dispatchers.Main) {
            val response = api.getTopRated()
            if (response.isSuccessful) {
                responseList = response.body()?.movieList ?:arrayListOf()
            }
            else{
                responseList = arrayListOf()
            }
        }
        return responseList

    }
}