package com.lanier.game3.domain.feature.market

import com.lanier.game3.domain.model.MarketModel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 01:47
 */
interface MarketRepository {

    suspend fun getAllProductsByType(type: Int, page: Int, limit: Int): Result<List<MarketModel>>

    suspend fun changeListed(marketId: Int, isListed: Boolean): Result<Boolean>
}