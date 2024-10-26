package com.lanier.game3.presentation.feature.crop.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.domain.model.CropModel
import com.lanier.game3.manager.presentation.R
import com.lanier.game3.presentation.composable.AppTopBar
import com.lanier.game3.presentation.ext.gotoCropPage
import com.lanier.game3.presentation.model.getSeasonStrResId
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:31
 */
@Destination
@Composable
fun CropListPage(
    navigator: DestinationsNavigator,
    viewmodel: CropListViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.initialization()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AppTopBar(
            title = stringResource(R.string.crop_list),
            onClickBack = {
                navigator.popBackStack()
            }
        )

        CropList(
            data = { viewmodel.crops },
            onLoadMore = {
                if (viewmodel.isLoading.not()) {
                    viewmodel.getCrops(refresh = false)
                }
            }
        ) { crop ->
            navigator.gotoCropPage(crop)
        }
    }
}

@Composable
private fun CropList(
    modifier: Modifier = Modifier,
    data: () -> SnapshotStateList<CropModel>,
    onLoadMore: () -> Unit,
    onClick: (CropModel) -> Unit,
) {
    val listState = rememberLazyListState()
    val reachedBottom by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size ==
                    listState.layoutInfo.totalItemsCount
        }
    }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { reachedBottom }
            .collect { isReachedBottom ->
                if (isReachedBottom) {
                    onLoadMore.invoke()
                }
            }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        itemsIndexed(
            items = data.invoke(),
            key = { index, data -> "${index}_${data.cropId}" }
        ) { _, data ->
            CropItem(
                crop = data,
                isLatest = false,
                onClick = { onClick.invoke(data) }
            )
        }
    }
}

@Composable
private fun CropItem(
    modifier: Modifier = Modifier,
    crop: CropModel,
    isLatest: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = "${crop.cropId}",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = crop.name,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = stringResource(getSeasonStrResId(crop.season)),
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (isLatest.not()) {
            VerticalDivider()
        }
    }
}