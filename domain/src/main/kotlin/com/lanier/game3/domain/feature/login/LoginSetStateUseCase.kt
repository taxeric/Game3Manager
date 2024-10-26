package com.lanier.game3.domain.feature.login

import com.lanier.game3.domain.model.LoginRespModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:24
 */
class LoginSetStateUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(model: LoginRespModel) {
        repository.setLoginState(model)
    }
}