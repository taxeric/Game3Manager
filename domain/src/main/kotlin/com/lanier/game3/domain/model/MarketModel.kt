package com.lanier.game3.domain.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:47
 */
data class MarketModel(
    val id: Int?,
    val isListed: Boolean,
    val itemId: Int?,
    val itemType: Int?,
    val name: String?,
    val price: Int?,
    val desc: String?,
)

data class MarketChangeListedModel(
    val marketId: Int,
    val listed: Boolean,
)