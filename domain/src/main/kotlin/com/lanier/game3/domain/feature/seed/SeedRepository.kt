package com.lanier.game3.domain.feature.seed

import com.lanier.game3.domain.model.SeedModel

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:34
 */
interface SeedRepository {

    suspend fun getSeeds(offset: Int, limit: Int) : Result<List<SeedModel>>

    suspend fun saveSeed(seedModel: SeedModel) : Result<Boolean>
}