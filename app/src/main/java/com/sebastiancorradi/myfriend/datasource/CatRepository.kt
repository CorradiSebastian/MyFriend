package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat
import javax.inject.Inject


interface ICatRepository {
    fun getCats(): List<Cat>
}