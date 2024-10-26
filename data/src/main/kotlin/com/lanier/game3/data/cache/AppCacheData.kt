package com.lanier.game3.data.cache

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 18:13
 */
object AppCacheData {

    var respToken: String? = null

    fun isLoggedIn() : Boolean {
        return respToken.isNullOrBlank().not()
    }
}