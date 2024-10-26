package com.lanier.game3.domain.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:14
 */
data class LoginReqModel(
    val account: String,
    val password: String,
)

data class LoginRespModel(
    val account: String,
    val balance: Int,
    val gender: Int,
    val id: Int,
    val lands: List<Land>,
    val token: String,
    val username: String
)
