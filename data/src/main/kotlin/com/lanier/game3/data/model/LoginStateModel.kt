package com.lanier.game3.data.model

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:11
 */
data class LoginStateModel(
    val isLoggedIn: MutableStateFlow<Boolean>
)