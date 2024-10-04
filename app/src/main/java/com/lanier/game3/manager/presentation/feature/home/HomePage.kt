package com.lanier.game3.manager.presentation.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 20:30
 */
@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage() {
    Box {
        Text(text = "home")
    }
}