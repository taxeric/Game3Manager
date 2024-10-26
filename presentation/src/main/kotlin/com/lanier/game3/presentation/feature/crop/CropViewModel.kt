package com.lanier.game3.presentation.feature.crop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lanier.game3.domain.model.Season
import com.lanier.game3.presentation.feature.navArgs
import com.lanier.game3.presentation.navmodel.CropNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/23 22:49
 */
@HiltViewModel
class CropViewModel @Inject constructor(
    handle: SavedStateHandle
): ViewModel() {

    private val navArgs = handle.navArgs<CropNavArgs>()

    var cropUiState by mutableStateOf(
        CropUiState(
            cropId = navArgs.cropId,
            inputSeedId = "${navArgs.seedId ?: ""}",
            inputName = navArgs.name ?: "",
            inputPrice = "${navArgs.price ?: ""}",
            season = when (navArgs.season) {
                1 -> Season.Spring
                2 -> Season.Summer
                3 -> Season.Autumn
                4 -> Season.Winter
                else -> Season.None
            },
        )
    )
        private set

    fun changeSeedId(newValue: String) {
        cropUiState = cropUiState.copy(
            inputSeedId = newValue
        )
    }

    fun changeName(newValue: String) {
        cropUiState = cropUiState.copy(
            inputName = newValue
        )
    }

    fun changePrice(newValue: String) {
        cropUiState = cropUiState.copy(
            inputPrice = newValue
        )
    }

    fun changeSeason(newValue: Season) {
        cropUiState = cropUiState.copy(
            season = newValue
        )
    }

    fun saveCrop() {
    }
}