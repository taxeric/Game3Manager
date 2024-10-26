package com.lanier.game3.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lanier.game3.manager.presentation.R

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:37
 */
val seasons = listOf(
    0 to R.string.season_none,
    1 to R.string.season_spring,
    2 to R.string.season_summer,
    3 to R.string.season_autumn,
    4 to R.string.season_winter,
)

fun getSeasonStrResId(season: Int) : Int {
    return when (season) {
        1 -> R.string.season_spring
        2 -> R.string.season_summer
        3 -> R.string.season_autumn
        4 -> R.string.season_winter
        else -> R.string.season_none
    }
}
