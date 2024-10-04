package com.lanier.game3.data.api

import com.lanier.game3.domain.model.BaseAPIModel
import com.lanier.game3.domain.model.LoginReqModel
import com.lanier.game3.domain.model.LoginRespModel
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:03
 */
interface Game3API {

    @POST("/")
    suspend fun login(@Body model: LoginReqModel) : BaseAPIModel<LoginRespModel>
}