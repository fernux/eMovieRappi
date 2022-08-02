package com.orion.emovie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orion.emovie.data.database.entities.MovieEntity


@Dao
 interface MovieDao {

    @Query(value = "SELECT * FROM movie WHERE type == 0 ORDER BY title ASC")
    suspend fun getAllMovieUp():MutableList<MovieEntity>

   @Query(value = "SELECT * FROM movie WHERE type == 1 ORDER BY title ASC")
    suspend fun getAllMovieTop():MutableList<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:MutableList<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovie()
}
