package com.orion.emovie.data.network


import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.data.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailService @Inject constructor(private val api:MovieApiClient) {
     lateinit var response:MovieDetailsModel
    lateinit var responseVideo:List<Video>

    suspend fun getMovieDetails(id:Int):MovieDetailsModel {

        withContext(Dispatchers.Main) {
            val call = api.getMovieDetails(id)
            if (call.isSuccessful) {
                response = call.body()!!
            }
        }
        return response

    }

    suspend fun getMovieVideo(id:Int):List<Video> {
        var isConnected: Boolean

        withContext(Dispatchers.Main) {
            val call = api.getTrailerDetails(id)
            if (call.isSuccessful) {
                responseVideo = call.body()!!.results
            }
        }
        return responseVideo

    }
}