package com.orion.emovie.domain.usecase


import com.orion.emovie.data.MovieRepository
import com.orion.emovie.data.database.entities.toDatabase
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.domain.model.toDomain
import javax.inject.Inject

class GetMovieToRatedUseCase @Inject constructor(private val repository : MovieRepository) {

    suspend operator fun invoke(): MutableList<MovieModel> {
        if (repository.getAllTopMovieFromDatabase().isEmpty()) {
            val movies = repository.getMovieTopRatedFromApi()
            if (movies.isNotEmpty()) {
                movies.forEach { it.type = 1 }
                repository.insertMovie(movies.map { it.toDatabase() }.toMutableList())
                return movies
            }
        } else {
        return     repository.getAllTopMovieFromDatabase().map { it.toDomain() }.toMutableList()
        }
        return arrayListOf()
    }
}




