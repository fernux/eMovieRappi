package com.orion.emovie.data.network

import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.data.model.MovieResponseModel
import com.orion.emovie.data.model.TrailersModel
import retrofit2.Response
import javax.inject.Inject

class MovieApiClientImpl @Inject constructor():MovieApiClient {
    override suspend fun getMovieDetails(id: Int): Response<MovieDetailsModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcoming(): Response<MovieResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRated(): Response<MovieResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrailerDetails(id: Int): Response<TrailersModel> {
        TODO("Not yet implemented")
    }

}