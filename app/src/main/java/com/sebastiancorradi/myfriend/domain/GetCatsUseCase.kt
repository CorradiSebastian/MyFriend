package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val catDataSource: ICatDataSource,
) {
    suspend operator  fun invoke(catsLoaded: Int): List<Cat> {
        val result = catDataSource.getCats(catsLoaded)
        return result?: listOf()
    }
}

