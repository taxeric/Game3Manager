package com.lanier.game3.presentation.feature.login

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 19:05
 */
data class LoginUiState(
    val inputAccount: String,
    val inputPassword: String,
) {

    val loginBtnEnabled: Boolean
        get() = inputPassword.isNotBlank() && inputAccount.isNotBlank()
}

data class HostAddressState(
    val address: String,
)

/**
 * 地址编辑对话框显隐状态
 */
sealed interface HostEditDialogUiState {

    data object Hide: HostEditDialogUiState
    data object Show: HostEditDialogUiState
}
