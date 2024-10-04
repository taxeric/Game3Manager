package com.lanier.game3.domain.feature.login

import com.lanier.game3.domain.model.LoginRespModel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:13
 */
interface LoginRepository {

    suspend fun login(account: String, password: String) : Result<LoginRespModel>
}