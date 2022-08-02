package com.orion.emovie.di

import android.content.Context
import androidx.room.Room
import com.orion.emovie.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val MOVIE_DATABASE_NAME = "movie"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)
     = Room.databaseBuilder(context, MovieDatabase::class.java,MOVIE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db:MovieDatabase) = db.getQuoteDao()


}