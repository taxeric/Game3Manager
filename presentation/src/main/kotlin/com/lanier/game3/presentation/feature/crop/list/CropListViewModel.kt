package com.lanier.game3.presentation.feature.crop.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.domain.feature.crop.GetCropsUseCase
import com.lanier.game3.domain.model.CropModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:50
 */
@HiltViewModel
class CropListViewModel @Inject constructor(
    private val getCropsUseCase: GetCropsUseCase,
) : ViewModel() {

    private var getCropsJob: Job? = null

    val crops = mutableStateListOf<CropModel>()
    var page: Int = 0
        private set
    var isEnd: Boolean = false
        private set

    var isLoading: Boolean = false
        private set

    private var isInitialization = false

    fun initialization() {
        if (isInitialization) return
        isInitialization = true
        getCrops(refresh = true)
    }

    fun getCrops(refresh: Boolean = false, limit: Int = 20) {
        if (refresh) page = 0
        val oldJob = getCropsJob
        getCropsJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            isLoading = true
            getCropsUseCase(
                offset = page,
                limit = limit
            )
                .onSuccess {
                    if (page <= 0) {
                        crops.clear()
                    }
                    crops.addAll(it)
                    if (it.size < limit) {
                        isEnd = true
                    } else {
                        page ++
                    }
                }
                .onFailure {
                    println(it)
                }
            isLoading = false
            getCropsJob = null
        }
    }
}