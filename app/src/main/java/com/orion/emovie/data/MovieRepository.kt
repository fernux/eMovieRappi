package com.orion.emovie.data

import com.orion.emovie.data.database.dao.MovieDao
import com.orion.emovie.data.database.entities.MovieEntity
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val  api : MovieService,
    private val movieDao: MovieDao,
) {
    suspend fun getMovieUpcomingFromApi(): MutableList<MovieModel> {
        return api.getMovieUpcoming()
    }
    suspend fun getMovieTopRatedFromApi(): MutableList<MovieModel> {
        return api.getMovieTopRated()
    }
     suspend fun getAllTopMovieFromDatabase(): MutableList<MovieEntity> {

        return movieDao.getAllMovieTop()
    }
     suspend fun getAllUpMovieFromDatabase(): MutableList<MovieEntity> {
        return movieDao.getAllMovieUp()
    }

    suspend fun insertMovie(movie:MutableList<MovieEntity>){
        movieDao.insertAll(movie)
    }


    suspend fun clearMovies(){
        movieDao.deleteAllMovie()
    }

}