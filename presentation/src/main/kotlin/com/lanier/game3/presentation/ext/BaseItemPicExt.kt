package com.lanier.game3.presentation.ext

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 21:48
 */
fun Int?.buildItemLoadUrl() : String {
    val mid = this ?: return ""
    val nid = mid.toString().replace("100728", "")
    val url = buildString {
        append("http://192.168.31.52:8080/resources/")
        append(nid)
        append("/crop.png")
    }
    println(url)
    return url
}
