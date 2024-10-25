package com.lanier.game3.manager.presentation.feature.seed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.domain.model.Season
import com.lanier.game3.manager.R
import com.lanier.game3.manager.presentation.composable.AppTopBar
import com.lanier.game3.manager.presentation.composable.SeasonSelection
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
            .verticalScroll(rememberScrollState())
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
        SeedEditContent(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            inputCropId = { viewmodel.seedUiState.inputCropId },
            onChangeInputCropId = { viewmodel.changeCropId(it) },
            inputName = { viewmodel.seedUiState.inputName },
            onChangeInputName = { viewmodel.changeName(it) },
            inputPrice = { viewmodel.seedUiState.inputPrice },
            onChangeInputPrice = { viewmodel.changePrice(it) },
            inputDesc = { viewmodel.seedUiState.inputDesc },
            onChangeDesc = { viewmodel.changeDesc(it) },
            inputSeason = { viewmodel.seedUiState.season },
            onChangeSeason = { viewmodel.changeSeason(it) },
            inputMaxHarvestCount = { viewmodel.seedUiState.inputMaxHarvestCount },
            onChangeMaxHarvestCount = { viewmodel.changeMaxHarvestCount(it) },
            inputCropExpPer = { viewmodel.seedUiState.inputCropExpPer },
            onChangeCropExpPer = { viewmodel.changeCropExpPer(it) },
            inputSingleHarvestAmount = { viewmodel.seedUiState.inputSingleHarvestAmount },
            onChangeSingleHarvestAmount = { viewmodel.changeSingleHarvestAmount(it) },
            inputStageInfo = { viewmodel.seedUiState.inputStageInfo },
            onChangeStageInfo = { viewmodel.changeStageInfo(it) },
            inputPlantLevel = { viewmodel.seedUiState.inputPlantLevel },
            onChangePlantLevel = { viewmodel.changePlantLevel(it) }
        )
    }
}

@Composable
private fun SeedEditContent(
    modifier: Modifier = Modifier,
    inputCropId: () -> String,
    onChangeInputCropId: (String) -> Unit,
    inputName: () -> String,
    onChangeInputName: (String) -> Unit,
    inputPrice: () -> String,
    onChangeInputPrice: (String) -> Unit,
    inputDesc: () -> String,
    onChangeDesc: (String) -> Unit,
    inputSeason: () -> Season,
    onChangeSeason: (Int) -> Unit,
    inputMaxHarvestCount: () -> String,
    onChangeMaxHarvestCount: (String) -> Unit,
    inputCropExpPer: () -> String,
    onChangeCropExpPer: (String) -> Unit,
    inputSingleHarvestAmount: () -> String,
    onChangeSingleHarvestAmount: (String) -> Unit,
    inputStageInfo: () -> String,
    onChangeStageInfo: (String) -> Unit,
    inputPlantLevel: () -> String,
    onChangePlantLevel: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputCropId.invoke(),
            onValueChange = onChangeInputCropId,
            label = {
                Text(text = stringResource(R.string.crop_id))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputDesc.invoke(),
            onValueChange = onChangeDesc,
            label = {
                Text(text = stringResource(R.string.description))
            },
        )
        SeasonSelection(
            selectedSeason = inputSeason.invoke(),
            onSeasonSelected = { onChangeSeason.invoke(it) }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputMaxHarvestCount.invoke(),
            onValueChange = onChangeMaxHarvestCount,
            label = {
                Text(text = stringResource(R.string.max_harvest_count))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputCropExpPer.invoke(),
            onValueChange = onChangeCropExpPer,
            label = {
                Text(text = stringResource(R.string.crop_exp_per))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputSingleHarvestAmount.invoke(),
            onValueChange = onChangeSingleHarvestAmount,
            label = {
                Text(text = stringResource(R.string.single_harvest_amount))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputStageInfo.invoke(),
            onValueChange = onChangeStageInfo,
            label = {
                Text(text = stringResource(R.string.stage_info))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputPlantLevel.invoke(),
            onValueChange = onChangePlantLevel,
            label = {
                Text(text = stringResource(R.string.plant_level))
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
