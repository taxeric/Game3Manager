package com.lanier.game3.data.di

import com.lanier.game3.data.api.Game3API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 21:59
 */
@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun provideGame3API() : Game3API {
        return Retrofit.Builder()
            .baseUrl("")
            .callFactory(okhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Game3API::class.java)
    }

    private fun okhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}