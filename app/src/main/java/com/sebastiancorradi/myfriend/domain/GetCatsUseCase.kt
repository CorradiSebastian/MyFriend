package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val catDataSource: ICatDataSource,
) {
    suspend operator  fun invoke(): List<Cat> {
        val result = catDataSource.getCats()
        return result?: listOf()
    }
}

