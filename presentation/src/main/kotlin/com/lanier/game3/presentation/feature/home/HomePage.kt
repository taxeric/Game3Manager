package com.lanier.game3.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lanier.game3.manager.presentation.R
import com.lanier.game3.presentation.composable.AppTopBar
import com.lanier.game3.presentation.ext.gotoCropListPage
import com.lanier.game3.presentation.ext.gotoMarketPage
import com.lanier.game3.presentation.ext.gotoSeedListPage
import com.lanier.game3.presentation.ext.gotoSeedPage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 20:30
 */
@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(
            hideBackBtn = true,
            onClickBack = {}
        )
        OutlinedButton(
            onClick = {
                navigator.gotoSeedListPage()
            }
        ) {
            Text(
                text = stringResource(R.string.seed_manage)
            )
        }

        OutlinedButton(
            onClick = {
                navigator.gotoCropListPage()
            }
        ) {
            Text(
                text = stringResource(R.string.crop_manage)
            )
        }

        OutlinedButton(
            onClick = {
                navigator.gotoMarketPage()
            }
        ) {
            Text(
                text = stringResource(R.string.market_manage)
            )
        }
    }
}