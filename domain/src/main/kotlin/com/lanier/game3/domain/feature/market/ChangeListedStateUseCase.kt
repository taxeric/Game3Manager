package com.lanier.game3.domain.feature.market

import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 02:08
 */
class ChangeListedStateUseCase @Inject constructor(
    private val repository: MarketRepository
) {

    suspend operator fun invoke(marketId: Int, listed: Boolean) : Result<Boolean> {
        return repository.changeListed(marketId, listed)
    }
}