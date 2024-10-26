package com.lanier.game3.domain.feature.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:17
 */
class LoginStateFlowUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(): StateFlow<Boolean> {
        return repository.getLoginStateFlow()
    }
}