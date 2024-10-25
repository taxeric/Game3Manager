package com.lanier.game3.data.feature

import com.lanier.game3.data.api.Game3API
import com.lanier.game3.data.ext.handleAPIResp
import com.lanier.game3.domain.feature.crop.CropRepository
import com.lanier.game3.domain.model.CropModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:37
 */
class CropRepositoryImpl @Inject constructor(
    private val api: Game3API
) : CropRepository {
    override suspend fun getCrops(offset: Int, limit: Int): Result<List<CropModel>> {
        return Result.runCatching {
            withContext(Dispatchers.IO) {
                api.getCrops(offset, limit)
                    .handleAPIResp()
            }
        }
    }

    override suspend fun saveCrop(crop: CropModel): Result<Boolean> {
        return Result.runCatching {
            withContext(Dispatchers.IO) {
                api.saveCrop(crop)
                    .handleAPIResp()
            }
        }
    }
}