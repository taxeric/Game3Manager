package com.lanier.game3.data.feature

import com.lanier.game3.data.api.Game3API
import com.lanier.game3.data.ext.handleAPIResp
import com.lanier.game3.domain.feature.market.MarketRepository
import com.lanier.game3.domain.model.MarketChangeListedModel
import com.lanier.game3.domain.model.MarketModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 02:09
 */
class MarketRepositoryImpl @Inject constructor(
    private val api: Game3API
) : MarketRepository {
    override suspend fun getAllProductsByType(
        type: Int,
        page: Int,
        limit: Int
    ): Result<List<MarketModel>> {
        return runCatching {
            withContext(Dispatchers.IO) {
                val offset = (page - 1) * limit
                api.getMarketProducts(type, offset, limit)
                    .handleAPIResp()
            }
        }
    }

    override suspend fun changeListed(marketId: Int, isListed: Boolean): Result<Boolean> {
        return runCatching {
            withContext(Dispatchers.IO) {
                api.changeListed(MarketChangeListedModel(marketId, isListed))
                    .handleAPIResp()
            }
        }
    }
}