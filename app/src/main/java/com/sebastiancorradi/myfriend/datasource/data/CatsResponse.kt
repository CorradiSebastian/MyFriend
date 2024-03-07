package com.sebastiancorradi.myfriend.datasource.data

import com.sebastiancorradi.myfriend.data.Cat

data class CatsResponse(
    val cats: List<Cat> = listOf(),
    val success: Boolean = false,
    val errorCode: Int = 0,
    val errorMessage: String? = null
) {

}