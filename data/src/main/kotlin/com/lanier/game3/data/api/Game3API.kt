package com.lanier.game3.data.api

import com.lanier.game3.domain.model.BaseAPIModel
import com.lanier.game3.domain.model.CropModel
import com.lanier.game3.domain.model.LoginReqModel
import com.lanier.game3.domain.model.LoginRespModel
import com.lanier.game3.domain.model.SeedModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:03
 */
interface Game3API {

    @POST("/login")
    suspend fun login(@Body model: LoginReqModel) : BaseAPIModel<LoginRespModel>

    @GET("/get-crops")
    suspend fun getCrops(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ) : BaseAPIModel<List<CropModel>>

    @POST("/upsert-crop")
    suspend fun saveCrop(
        @Body model: CropModel
    ) : BaseAPIModel<Boolean>

    @GET("/get-seed")
    suspend fun getSeeds(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ) : BaseAPIModel<List<SeedModel>>

    @POST("/upsert-seed")
    suspend fun saveSeed(
        @Body model: SeedModel
    ) : BaseAPIModel<Boolean>
}