package com.sebastiancorradi.myfriend.datasource.repository

import com.sebastiancorradi.myfriend.datasource.data.CatsResponse


interface ICatRepository {
    suspend fun getCats(startFrom: Int = 0): CatsResponse?
}