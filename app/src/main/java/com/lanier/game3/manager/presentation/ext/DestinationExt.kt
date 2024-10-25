package com.lanier.game3.manager.presentation.ext

import androidx.navigation.NavController
import com.lanier.game3.domain.model.Crop
import com.lanier.game3.manager.presentation.feature.destinations.CropPageDestination
import com.lanier.game3.manager.presentation.feature.destinations.SeedPageDestination
import com.lanier.game3.manager.presentation.navmodel.CropNavArgs
import com.lanier.game3.manager.presentation.navmodel.SeedNavArgs
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:39
 */
fun DestinationsNavigator.gotoCropPage(
    seedId: Int?,
    name: String?,
    price: Int?,
    season: Int?,
    cropId: Int? = null,
) {
    navigate(
        CropPageDestination(
            CropNavArgs(
                cropId = cropId,
                seedId = seedId,
                name = name,
                price = price,
                season = season,
            )
        )
    ) {
        launchSingleTop = true
    }
}

fun DestinationsNavigator.gotoSeedPage(
    cropId: Int?,
    name: String?,
    price: Int?,
    desc: String?,
    season: Int?,
    maxHarvestCount: Int?,
    cropExpPer: Int?,
    singleHarvestAmount: Int?,
    stageInfo: String?,
    plantLevel: Int?,
    seedId: Int? = null,
) {
    navigate(
        SeedPageDestination(
            SeedNavArgs(
                seedId = seedId,
                cropId = cropId,
                name = name,
                price = price,
                desc = desc,
                season = season,
                maxHarvestCount = maxHarvestCount,
                cropExpPer = cropExpPer,
                singleHarvestAmount = singleHarvestAmount,
                stageInfo = stageInfo,
                plantLevel = plantLevel
            )
        )
    ) {
        launchSingleTop = true
    }
}
