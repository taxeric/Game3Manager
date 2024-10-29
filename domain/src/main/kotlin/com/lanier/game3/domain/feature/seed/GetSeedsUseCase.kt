package com.lanier.game3.domain.feature.seed

import com.lanier.game3.domain.model.SeedModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/29 23:06
 */
class GetSeedsUseCase @Inject constructor(
    private val repository: SeedRepository
) {

    suspend operator fun invoke(offset: Int, limit: Int) : Result<List<SeedModel>> {
        return repository.getSeeds(offset, limit)
    }
}