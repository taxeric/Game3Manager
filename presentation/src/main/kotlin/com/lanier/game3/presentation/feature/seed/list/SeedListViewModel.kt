package com.lanier.game3.presentation.feature.seed.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.domain.feature.seed.GetSeedsUseCase
import com.lanier.game3.domain.model.SeedModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/29 23:05
 */
@HiltViewModel
class SeedListViewModel @Inject constructor(
    private val getSeedsUseCase: GetSeedsUseCase
) : ViewModel() {

    private var getSeedsJob: Job? = null

    var pageScrollItemIndex by mutableIntStateOf(0)
        private set
    var pageScrollOffset by mutableIntStateOf(0)
        private set

    val seeds = mutableStateListOf<SeedModel>()
    var page: Int = 1
        private set
    var isEnd: Boolean = false
        private set

    var isLoading: Boolean = false
        private set

    private var isInitialization = false

    fun initialization() {
        if (isInitialization) return
        isInitialization = true
        getSeeds(refresh = true)
    }

    fun updateScrollPosition(newIndex: Int, newOffset: Int) {
        pageScrollItemIndex = newIndex
        pageScrollOffset = newOffset
    }

    fun getSeeds(refresh: Boolean = false, limit: Int = 20) {
        if (refresh) {
            page = 1
        } else {
            if (isEnd) return
        }
        val oldJob = getSeedsJob
        getSeedsJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            isLoading = true
            delay(1000L)
            getSeedsUseCase(
                offset = (page - 1) * limit,
                limit = limit
            )
                .onSuccess {
                    if (page <= 1) {
                        seeds.clear()
                    }
                    seeds.addAll(it)
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
            getSeedsJob = null
        }
    }
}