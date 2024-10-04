package com.lanier.game3.manager.presentation.feature.login

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 19:05
 */
data class LoginState(
    val inputAccount: String,
    val inputPassword: String,
) {

    val loginBtnEnabled: Boolean
        get() = inputPassword.isNotBlank() && inputAccount.isNotBlank()
}
