package com.orion.emovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orion.emovie.data.database.dao.MovieDao
import com.orion.emovie.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun getQuoteDao(): MovieDao
}