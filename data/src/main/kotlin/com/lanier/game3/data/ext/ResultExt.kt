package com.lanier.game3.data.ext

import com.lanier.game3.domain.exception.ReqFailedException
import com.lanier.game3.domain.model.BaseAPIModel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:23
 */
fun <T> BaseAPIModel<T>.handleAPIResp(): T {
    return if (code == 0) {
        Result.success(data)
    } else {
        Result.failure(ReqFailedException(code, message))
    }.getOrThrow()
}