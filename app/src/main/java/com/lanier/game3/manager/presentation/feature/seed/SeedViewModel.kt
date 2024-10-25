package com.lanier.game3.manager.presentation.feature.seed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lanier.game3.domain.model.Season
import com.lanier.game3.manager.presentation.feature.navArgs
import com.lanier.game3.manager.presentation.navmodel.SeedNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/25 00:16
 */
@HiltViewModel
class SeedViewModel @Inject constructor(
    handle: SavedStateHandle
) : ViewModel() {

    private val navArgs = handle.navArgs<SeedNavArgs>()

    var seedUiState by mutableStateOf(
        SeedUiState(
            seedId = navArgs.seedId,
            inputCropId = "${navArgs.cropId ?: ""}",
            inputName = navArgs.name ?: "",
            inputPrice = "${navArgs.price ?: ""}",
            inputDesc = navArgs.desc ?: "",
            season = when (navArgs.season) {
                1 -> Season.Spring
                2 -> Season.Summer
                3 -> Season.Autumn
                4 -> Season.Winter
                else -> Season.None
            },
            inputMaxHarvestCount = "${navArgs.maxHarvestCount ?: ""}",
            inputSingleHarvestAmount = "${navArgs.singleHarvestAmount ?: ""}",
            inputCropExpPer = "${navArgs.cropExpPer ?: ""}",
            inputStageInfo = navArgs.stageInfo ?: "",
            inputPlantLevel = "${navArgs.plantLevel ?: ""}"
        )
    )
        private set

    fun changeCropId(newValue: String) {
        seedUiState = seedUiState.copy(
            inputCropId = newValue
        )
    }

    fun changeName(newValue: String) {
        seedUiState = seedUiState.copy(
            inputName = newValue
        )
    }

    fun changePrice(newValue: String) {
        seedUiState = seedUiState.copy(
            inputPrice = newValue
        )
    }

    fun changeDesc(newValue: String) {
        seedUiState = seedUiState.copy(
            inputDesc = newValue
        )
    }

    fun changeSeason(newValue: Int) {
        seedUiState = seedUiState.copy(
            season = when (newValue) {
                1 -> Season.Spring
                2 -> Season.Summer
                3 -> Season.Autumn
                4 -> Season.Winter
                else -> Season.None
            }
        )
    }

    fun changeMaxHarvestCount(newValue: String) {
        seedUiState = seedUiState.copy(
            inputMaxHarvestCount = newValue
        )
    }

    fun changeSingleHarvestAmount(newValue: String) {
        seedUiState = seedUiState.copy(
            inputSingleHarvestAmount = newValue
        )
    }

    fun changeCropExpPer(newValue: String) {
        seedUiState = seedUiState.copy(
            inputCropExpPer = newValue
        )
    }

    fun changeStageInfo(stageInfo: String) {
        seedUiState = seedUiState.copy(
            inputStageInfo = stageInfo
        )
    }

    fun changePlantLevel(newValue: String) {
        seedUiState = seedUiState.copy(
            inputPlantLevel = newValue
        )
    }

    fun saveSeed() {
    }
}