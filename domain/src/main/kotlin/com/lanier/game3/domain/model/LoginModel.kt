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
    val userId: Int,
    val account: String,
    val username: String,
    val gender: Int,
)
