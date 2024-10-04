package com.lanier.game3.data.feature

import com.lanier.game3.data.api.Game3API
import com.lanier.game3.data.ext.handleAPIResp
import com.lanier.game3.domain.feature.login.LoginRepository
import com.lanier.game3.domain.model.LoginReqModel
import com.lanier.game3.domain.model.LoginRespModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:16
 */
class LoginRepositoryImpl @Inject constructor(
    private val api: Game3API,
) : LoginRepository {
    override suspend fun login(account: String, password: String): Result<LoginRespModel> {
        val reqModel = LoginReqModel(
            account = account,
            password = password,
        )
        return Result.runCatching {
            withContext(Dispatchers.IO) {
                api.login(reqModel)
            }.handleAPIResp()
        }
    }
}