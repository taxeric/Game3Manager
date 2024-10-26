package com.lanier.game3.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 19:44
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val isLoggedIn = false
}