package com.lanier.game3.presentation.feature.crop

import com.lanier.game3.domain.model.Season

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/23 22:50
 */
data class CropUiState(
    val cropId: Int?,
    val inputSeedId: String,
    val inputName: String,
    val inputPrice: String,
    val season: Season,
)
