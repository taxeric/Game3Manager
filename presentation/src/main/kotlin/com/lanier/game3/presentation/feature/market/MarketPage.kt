package com.lanier.game3.presentation.feature.market

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.domain.model.MarketModel
import com.lanier.game3.domain.model.MarketType
import com.lanier.game3.manager.presentation.R
import com.lanier.game3.presentation.composable.AppTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 02:28
 */
@Destination
@Composable
fun MarketPage(
    viewmodel: MarketViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.initialization(MarketType.Seed)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(
            title = stringResource(R.string.market_manage),
            onClickBack = {
                navigator.popBackStack()
            }
        )

        MarketList(
            modifier = Modifier
                .fillMaxWidth(),
            startIndex = { viewmodel.pageScrollItemIndex },
            startOffset = { viewmodel.pageScrollOffset },
            onChangeScrollPosition = { newIndex, newOffset ->
                viewmodel.updateScrollPosition(newIndex, newOffset)
            },
            items = { viewmodel.marketItems },
            onLoadMore = { viewmodel.load() },
            onClick = {
            }
        )
    }
}

@Composable
private fun MarketList(
    modifier: Modifier = Modifier,
    startIndex: () -> Int,
    startOffset: () -> Int,
    onChangeScrollPosition: (Int, Int) -> Unit,
    items: () -> SnapshotStateList<MarketModel>,
    onLoadMore: () -> Unit,
    onClick: (MarketModel) -> Unit,
) {

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = startIndex.invoke(),
        initialFirstVisibleItemScrollOffset = startOffset.invoke(),
    )
    val reachedBottom by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size ==
                    listState.layoutInfo.totalItemsCount
        }
    }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (newIndex, newOffset) ->
                onChangeScrollPosition.invoke(newIndex, newOffset)
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
        state = listState,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        itemsIndexed(
            items = items.invoke(),
            key = { index, data -> "${index}_${data.id}" }
        ) { _, data ->
            MarketItem(model = data, onClick = { onClick.invoke(data) })
        }
    }
}

@Composable
private fun MarketItem(
    modifier: Modifier = Modifier,
    model: MarketModel,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray)
        ) {
            // 占位
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = model.name ?: "Invalid item",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${model.price ?: "?"}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(if (model.isListed) R.string.listed_true else R.string.listed_false),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
