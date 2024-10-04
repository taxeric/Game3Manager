package com.lanier.game3.manager.presentation.feature.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Desc:    
 * Author:  幻弦让叶
 * Date:    2024/10/4 19:04
 */
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var loginJob: Job? = null

    var loginState by mutableStateOf(
        LoginState(
            inputAccount = "",
            inputPassword = ""
        )
    )
        private set

    var hostAddressState by mutableStateOf(
        HostAddressState(
            address = "", // todo 这里读取本地存储的地址
        )
    )
        private set

    var hostEditDialogUiState: HostEditDialogUiState by mutableStateOf(
        HostEditDialogUiState.Hide
    )
        private set

    fun onAccountChanged(newValue: String) {
        loginState = loginState.copy(
            inputAccount = newValue
        )
    }

    fun onPasswordChanged(newValue: String) {
        loginState = loginState.copy(
            inputPassword = newValue
        )
    }

    fun onHostAddressChanged(newValue: String) {
        hostAddressState = hostAddressState.copy(
            address = newValue
        )
    }

    fun onHostEditDialogUiStateChanged(state: HostEditDialogUiState) {
        hostEditDialogUiState = state
    }

    fun login(
        loadingState: MutableState<Boolean>?
    ) {
        val oldJob = loginJob
        loginJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            loadingState?.value = true
            delay(2000L)
            loadingState?.value = false
            loginJob = null
        }
    }

    fun saveHostAddress() {
    }
}