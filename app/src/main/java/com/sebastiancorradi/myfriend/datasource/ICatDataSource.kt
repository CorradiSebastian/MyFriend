package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat

interface ICatDataSource {
    suspend fun getCats(): List<Cat>?
}