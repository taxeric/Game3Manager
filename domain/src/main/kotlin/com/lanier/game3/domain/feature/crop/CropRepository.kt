package com.lanier.game3.domain.feature.crop

import com.lanier.game3.domain.model.CropModel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:33
 */
interface CropRepository {

    suspend fun getCrops(offset: Int, limit: Int): Result<List<CropModel>>

    suspend fun saveCrop(crop: CropModel): Result<Boolean>
}
