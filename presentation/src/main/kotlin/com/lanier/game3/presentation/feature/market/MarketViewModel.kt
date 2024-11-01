package com.lanier.game3.presentation.feature.market

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.domain.feature.market.GetAllProductsUseCase
import com.lanier.game3.domain.model.MarketModel
import com.lanier.game3.domain.model.MarketType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 01:46
 */
@HiltViewModel
class MarketViewModel @Inject constructor(
    private val getAllMarketUseCase: GetAllProductsUseCase
) : ViewModel() {

    var pageScrollItemIndex by mutableIntStateOf(0)
        private set
    var pageScrollOffset by mutableIntStateOf(0)
        private set

    var marketType: MarketType? = null

    var page: Int = 1
        private set
    var isEnd: Boolean = false
        private set

    var isLoading: Boolean = false
        private set

    val marketItems = mutableStateListOf<MarketModel>()

    private var getMarketListJob: Job? = null

    private var isInitialization = false

    fun initialization() {
        if (isInitialization) return
        isInitialization = true
        load(refresh = true)
    }

    fun updateScrollPosition(newIndex: Int, newOffset: Int) {
        pageScrollItemIndex = newIndex
        pageScrollOffset = newOffset
    }

    fun load(refresh: Boolean = false, limit: Int = 20) {
        val mType = marketType ?: return
        if (isLoading) return
        if (refresh) {
            page = 1
        } else {
            if (isEnd) return
        }
        val oldJob = getMarketListJob
        getMarketListJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            isLoading = true
            getAllMarketUseCase.invoke(
                type = mType.type,
                page = page,
                limit = limit
            ).onSuccess {
                if (refresh) {
                    marketItems.clear()
                }
                marketItems.addAll(it)
                if (it.size < limit) {
                    isEnd = true
                } else {
                    page ++
                }
            }.onFailure {
                println(it)
            }
            isLoading = false
            getMarketListJob = null
        }
    }
}