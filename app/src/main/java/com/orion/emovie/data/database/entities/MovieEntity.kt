package com.orion.emovie.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orion.emovie.data.model.MovieModel


@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id:Int =1,
    @ColumnInfo(name = "title")val title:String,
    @ColumnInfo(name = "poster_path")val posterPath:String,
    @ColumnInfo(name = "release_date")val releaseDate:String,
    @ColumnInfo(name = "original_language")val language:String,
    @ColumnInfo(name = "type")val type:Int,

    )

fun MovieModel.toDatabase() = MovieEntity(id = id, title = title, posterPath = posterPath, releaseDate = releaseDate, language = language, type = type)

