package com.lanier.game3.domain.feature.login

import com.lanier.game3.domain.model.LoginRespModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 14:55
 */
class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {

    suspend operator fun invoke(
        account: String,
        password: String,
    ) : Result<LoginRespModel> {
        return repository.login(account, password)
    }
}