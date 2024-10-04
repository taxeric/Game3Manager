package com.lanier.game3.manager.presentation.feature.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.manager.R
import com.lanier.game3.manager.presentation.feature.login.hostedit.HostEditDialog
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.End),
            onClick = {
                viewModel.onHostEditDialogUiStateChanged(
                    HostEditDialogUiState.Show
                )
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "settings"
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.input_account))
            },
            value = viewModel.loginState.inputAccount,
            onValueChange = {
                viewModel.onAccountChanged(it)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.input_password))
            },
            value = viewModel.loginState.inputPassword,
            onValueChange = {
                viewModel.onPasswordChanged(it)
            }
        )
        Spacer(modifier = Modifier.height(36.dp))
        LoginBtn {
            viewModel.login(it)
        }
    }

    HostEditDialog(
        state = viewModel.hostEditDialogUiState,
        defValue = { viewModel.hostAddressState.address },
        onDismissRequest = { needSave ->
            if (needSave == true) {
                viewModel.saveHostAddress()
            }
            viewModel.onHostEditDialogUiStateChanged(
                HostEditDialogUiState.Hide
            )
        },
        onChanged = { newAddress ->
            viewModel.onHostAddressChanged(newAddress)
        }
    )
}

@Composable
private fun LoginBtn(
    modifier: Modifier = Modifier,
    onClick: (loadingState: MutableState<Boolean>) -> Unit,
) {
    val loadingState = remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .width(128.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .clickable { onClick.invoke(loadingState) },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = !loadingState.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Text(
                text = stringResource(R.string.text_login),
            )
        }
        AnimatedVisibility(
            visible = loadingState.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(16.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        }
    }
}
