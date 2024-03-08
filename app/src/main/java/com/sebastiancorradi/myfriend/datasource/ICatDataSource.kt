package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat

interface ICatDataSource {
    suspend fun getCats(startFrom: Int): List<Cat>?
}