package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat
import javax.inject.Inject

class CatDataSource @Inject constructor(
    private val catRepository: ICatRepository,
):ICatDataSource{
    override fun getCats(): List<Cat> {
        return catRepository.getCats()
    }

}