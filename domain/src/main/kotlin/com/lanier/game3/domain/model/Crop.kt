package com.lanier.game3.domain.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/23 23:31
 */
data class Crop(
    val cropId: Int,
    val seedId: Int,
    val name: String,
    val price: Int,
    val season: Int,
)
