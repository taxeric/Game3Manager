package com.lanier.game3.domain.feature.crop

import com.lanier.game3.domain.model.CropModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:50
 */
class GetCropsUseCase @Inject constructor(
    private val repository: CropRepository
) {

    suspend operator fun invoke(offset: Int, limit: Int) : Result<List<CropModel>> {
        return repository.getCrops(offset, limit)
    }
}