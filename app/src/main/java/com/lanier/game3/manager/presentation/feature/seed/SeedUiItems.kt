package com.lanier.game3.manager.presentation.feature.seed

import com.lanier.game3.domain.model.Season

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/25 00:24
 */
data class SeedUiState(
    val seedId: Int?,
    val inputCropId: String,
    val inputName: String,
    val inputPrice: String,
    val inputDesc: String,
    val season: Season,
    val inputMaxHarvestCount: String,
    val inputCropExpPer: String,
    val inputSingleHarvestAmount: String,
    val inputStageInfo: String,
    val inputPlantLevel: String,
)
