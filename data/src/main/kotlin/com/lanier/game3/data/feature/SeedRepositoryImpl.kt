package com.lanier.game3.data.feature

import com.lanier.game3.data.api.Game3API
import com.lanier.game3.data.ext.handleAPIResp
import com.lanier.game3.domain.feature.seed.SeedRepository
import com.lanier.game3.domain.model.SeedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:38
 */
class SeedRepositoryImpl @Inject constructor(
    private val api: Game3API
) : SeedRepository {
    override suspend fun getSeeds(offset: Int, limit: Int): Result<List<SeedModel>> {
        return Result.runCatching {
            withContext(Dispatchers.IO) {
                api.getSeeds(offset, limit)
                    .handleAPIResp()
            }
        }
    }

    override suspend fun saveSeed(seedModel: SeedModel): Result<Boolean> {
        return Result.runCatching {
            withContext(Dispatchers.IO) {
                api.saveSeed(seedModel)
                    .handleAPIResp()
            }
        }
    }
}