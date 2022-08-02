package com.orion.emovie.domain.usecase


import com.orion.emovie.data.MovieRepository
import com.orion.emovie.data.database.entities.toDatabase
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.domain.model.toDomain
import javax.inject.Inject

class GetMovieUpcomingUseCase@Inject constructor(private val repository : MovieRepository){

    suspend operator fun invoke():MutableList<MovieModel> {

        if (repository.getAllUpMovieFromDatabase().isEmpty()) {
            val movies = repository.getMovieUpcomingFromApi()
            if (movies.isNotEmpty()) {
                movies.forEach { it.type = 0 }
                repository.insertMovie(movies.map { it.toDatabase() }.toMutableList())
                return movies
            }
        } else {
            return repository.getAllUpMovieFromDatabase().map { it.toDomain() }.toMutableList()
        }
        return arrayListOf()
    }


}