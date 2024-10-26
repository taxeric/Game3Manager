package com.lanier.game3.data.di

import com.lanier.game3.data.cache.AppCacheData
import com.lanier.game3.data.model.LoginStateModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:10
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStateModule {

    @Provides
    @Singleton
    fun providerLoginState(): LoginStateModel {
        return LoginStateModel(
            MutableStateFlow(
                value = AppCacheData.isLoggedIn()
            )
        )
    }
}