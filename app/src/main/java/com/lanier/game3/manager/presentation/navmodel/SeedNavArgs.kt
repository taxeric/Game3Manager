package com.lanier.game3.manager.presentation.navmodel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/25 00:18
 */
data class SeedNavArgs(
    val seedId: Int? = null,
    val cropId: Int?,
    val name: String?,
    val price: Int?,
    val desc: String?,
    val season: Int?,
    val maxHarvestCount: Int?,
    val cropExpPer: Int?,
    val singleHarvestAmount: Int?,
    val stageInfo: String?,
    val plantLevel: Int?,
)
