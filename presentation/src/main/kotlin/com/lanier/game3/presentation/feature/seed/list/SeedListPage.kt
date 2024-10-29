package com.lanier.game3.presentation.feature.seed.list

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
import com.lanier.game3.domain.model.SeedModel
import com.lanier.game3.manager.presentation.R
import com.lanier.game3.presentation.composable.AppTopBar
import com.lanier.game3.presentation.ext.gotoSeedPage
import com.lanier.game3.presentation.model.getSeasonStrResId
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/29 23:05
 */
@Destination
@Composable
fun SeedListPage(
    navigator: DestinationsNavigator,
    viewmodel: SeedListViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.initialization()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AppTopBar(
            title = stringResource(R.string.seed_list),
            onClickBack = {
                navigator.popBackStack()
            }
        )

        CropList(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            startIndex = { viewmodel.pageScrollItemIndex },
            startOffset = { viewmodel.pageScrollOffset },
            onChangeScrollPosition = { newIndex, newOffset ->
                viewmodel.updateScrollPosition(
                    newIndex = newIndex,
                    newOffset = newOffset,
                )
            },
            data = { viewmodel.seeds },
            onLoadMore = {
                if (viewmodel.isLoading.not()) {
                    viewmodel.getSeeds(refresh = false)
                }
            }
        ) { seed ->
            navigator.gotoSeedPage(seed)
        }
    }
}

@Composable
private fun CropList(
    modifier: Modifier = Modifier,
    startIndex: () -> Int,
    startOffset: () -> Int,
    onChangeScrollPosition: (Int, Int) -> Unit,
    data: () -> SnapshotStateList<SeedModel>,
    onLoadMore: () -> Unit,
    onClick: (SeedModel) -> Unit,
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = startIndex.invoke(),
        initialFirstVisibleItemScrollOffset = startOffset.invoke(),
    )
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (newIndex, newOffset) ->
                onChangeScrollPosition.invoke(newIndex, newOffset)
            }
    }

    val reachedBottom by remember {
        derivedStateOf {
            val visibleItemIndex = listState.firstVisibleItemIndex
            val visibleItemSize = listState.layoutInfo.visibleItemsInfo.size
            val totalCount = listState.layoutInfo.totalItemsCount
            visibleItemIndex + visibleItemSize == totalCount
        }
    }

    LaunchedEffect(key1 = Unit) {
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
    ) {
        itemsIndexed(
            items = data.invoke(),
            key = { index, data -> "${index}_${data.cropId}" }
        ) { _, data ->
            SeedItem(
                seed = data,
                onClick = { onClick.invoke(data) }
            )
        }
    }
}

@Composable
private fun SeedItem(
    modifier: Modifier = Modifier,
    seed: SeedModel,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(8.dp)
    ) {
        Text(
            text = "${seed.cropId}",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = seed.name,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = stringResource(getSeasonStrResId(seed.season)),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${seed.price}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}