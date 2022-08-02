package com.orion.emovie.di


import com.orion.emovie.BuildConfig
import com.orion.emovie.data.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = BuildConfig.URL_MOVIE
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)     }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()

            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }



    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): MovieApiClient {
            return retrofit.create(MovieApiClient::class.java)
    }
}
