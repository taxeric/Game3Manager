package com.lanier.game3.presentation.ext

import com.lanier.game3.domain.model.CropModel
import com.lanier.game3.domain.model.SeedModel
import com.lanier.game3.presentation.feature.destinations.CropListPageDestination
import com.lanier.game3.presentation.feature.destinations.CropPageDestination
import com.lanier.game3.presentation.feature.destinations.SeedListPageDestination
import com.lanier.game3.presentation.feature.destinations.SeedPageDestination
import com.lanier.game3.presentation.navmodel.CropNavArgs
import com.lanier.game3.presentation.navmodel.SeedNavArgs
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:39
 */
fun DestinationsNavigator.gotoCropListPage() {
    navigate(
        CropListPageDestination
    ) {
        launchSingleTop = true
    }
}

fun DestinationsNavigator.gotoSeedListPage() {
    navigate(
        SeedListPageDestination
    ) {
        launchSingleTop = true
    }
}

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

fun DestinationsNavigator.gotoCropPage(
    crop: CropModel
) {
    navigate(
        CropPageDestination(
            CropNavArgs(
                cropId = crop.cropId,
                seedId = crop.seedId,
                name = crop.name,
                price = crop.price,
                season = crop.season,
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

fun DestinationsNavigator.gotoSeedPage(
    seed: SeedModel
) {
    navigate(
        SeedPageDestination(
            SeedNavArgs(
                seedId = seed.seedId,
                cropId = seed.cropId,
                name = seed.name,
                price = seed.price,
                desc = seed.desc,
                season = seed.season,
                maxHarvestCount = seed.maxHarvestCount,
                cropExpPer = seed.cropExpPer,
                singleHarvestAmount = seed.singleHarvestAmount,
                stageInfo = seed.stageInfo,
                plantLevel = seed.plantLevel
            )
        )
    ) {
        launchSingleTop = true
    }
}
