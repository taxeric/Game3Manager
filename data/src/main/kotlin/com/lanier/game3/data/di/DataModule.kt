package com.lanier.game3.data.di

import com.lanier.game3.data.feature.CropRepositoryImpl
import com.lanier.game3.data.feature.LoginRepositoryImpl
import com.lanier.game3.data.feature.MarketRepositoryImpl
import com.lanier.game3.data.feature.SeedRepositoryImpl
import com.lanier.game3.domain.feature.crop.CropRepository
import com.lanier.game3.domain.feature.login.LoginRepository
import com.lanier.game3.domain.feature.market.MarketRepository
import com.lanier.game3.domain.feature.seed.SeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 16:05
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl) : LoginRepository

    @Binds
    abstract fun bindCropRepository(cropRepositoryImpl: CropRepositoryImpl) : CropRepository

    @Binds
    abstract fun bindSeedRepository(seedRepositoryImpl: SeedRepositoryImpl) : SeedRepository

    @Binds
    abstract fun bindMarketRepository(marketRepositoryImpl: MarketRepositoryImpl) : MarketRepository
}