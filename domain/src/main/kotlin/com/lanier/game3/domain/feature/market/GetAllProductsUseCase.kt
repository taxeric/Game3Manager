package com.lanier.game3.domain.feature.market

import com.lanier.game3.domain.model.MarketModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 02:05
 */
class GetAllProductsUseCase @Inject constructor(
    private val repository: MarketRepository
) {

    suspend operator fun invoke(type: Int, page: Int, limit: Int) : Result<List<MarketModel>> {
        return repository.getAllProductsByType(type, page, limit)
    }
}