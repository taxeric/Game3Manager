package com.lanier.game3.manager.presentation.feature.crop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.manager.R
import com.lanier.game3.manager.presentation.composable.AppTopBar
import com.lanier.game3.manager.presentation.navmodel.CropNavArgs
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Desc:    作物管理
 * Author:  幻弦让叶
 * Date:    2024/10/23 22:47
 */
@Destination(
    navArgsDelegate = CropNavArgs::class
)
@Composable
fun CropPage(
    viewmodel: CropViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(
            title = stringResource(R.string.crop_manage),
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
        CropEditContent(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            inputSeedId = { viewmodel.cropUiState.inputSeedId },
            onChangeInputSeedId = { viewmodel.changeSeedId(it) },
            inputName = { viewmodel.cropUiState.inputName },
            onChangeInputName = { viewmodel.changeName(it) },
            inputPrice = { viewmodel.cropUiState.inputPrice },
            onChangeInputPrice = { viewmodel.changePrice(it) }
        )
    }
}

@Composable
private fun CropEditContent(
    modifier: Modifier = Modifier,
    inputSeedId: () -> String,
    onChangeInputSeedId: (String) -> Unit,
    inputName: () -> String,
    onChangeInputName: (String) -> Unit,
    inputPrice: () -> String,
    onChangeInputPrice: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputSeedId.invoke(),
            onValueChange = onChangeInputSeedId,
            label = {
                Text(text = stringResource(R.string.seed_id))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputName.invoke(),
            onValueChange = onChangeInputName,
            label = {
                Text(text = stringResource(R.string.name))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputPrice.invoke(),
            onValueChange = onChangeInputPrice,
            label = {
                Text(text = stringResource(R.string.price))
            },
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
