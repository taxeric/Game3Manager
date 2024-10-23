package com.lanier.game3.manager.presentation.feature.crop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.manager.R
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
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewmodel.cropUiState.inputSeedId,
            onValueChange = { viewmodel.changeSeedId(it) },
            label = {
                Text(text = stringResource(R.string.seed_id))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewmodel.cropUiState.inputName,
            onValueChange = { viewmodel.changeName(it) },
            label = {
                Text(text = stringResource(R.string.name))
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewmodel.cropUiState.inputPrice,
            onValueChange = { viewmodel.changePrice(it) },
            label = {
                Text(text = stringResource(R.string.price))
            },
        )
    }
}
