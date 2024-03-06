package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat

interface ICatDataSource {
    fun getCats(): List<Cat>
}