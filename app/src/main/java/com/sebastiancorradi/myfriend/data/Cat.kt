package com.sebastiancorradi.myfriend.data

data class Cat(
    val id: String= "",
    val tags: List<String> = listOf(),
    val owner: String = "",
    val createdAt: String = "",
    val upatedAt: String = ""
) {}