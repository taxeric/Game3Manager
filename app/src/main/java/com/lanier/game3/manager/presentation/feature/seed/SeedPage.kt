package com.lanier.game3.manager.presentation.feature.seed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.manager.R
import com.lanier.game3.manager.presentation.composable.AppTopBar
import com.lanier.game3.manager.presentation.navmodel.SeedNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Desc:    种子管理
 * Author:  幻弦让叶
 * Date:    2024/10/25 00:16
 */
@Destination(
    navArgsDelegate = SeedNavArgs::class
)
@Composable
fun SeedPage(
    viewmodel: SeedViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(
            title = stringResource(R.string.seed_manage),
            content = {
                SaveButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16F.dp)
                ) { loadingState ->
                }
            },
            onClickBack = {
            }
        )
    }
}

@Composable
private fun SaveButton(
    modifier: Modifier = Modifier,
    onSave: (loadingState: MutableState<Boolean>) -> Unit,
) {
    val saveLoadingState = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .width(64.dp)
            .clickable { onSave.invoke(saveLoadingState) },
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = !saveLoadingState.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Text(
                modifier = Modifier,
                text = "Save",
                style = TextStyle(
                    color = colorResource(id = R.color.color_333333),
                ),
            )
        }
        AnimatedVisibility(
            visible = saveLoadingState.value,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(18.dp),
                color = colorResource(id = R.color.color_333333),
                strokeWidth = 2.dp
            )
        }
    }
}
