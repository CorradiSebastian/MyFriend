package com.sebastiancorradi.myfriend.datasource

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.repository.ICatRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.ICatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.ICatRemoteRepository
import javax.inject.Inject

class CatDataSource @Inject constructor(
    private val catRepository: ICatRepository,
):ICatDataSource{
    override suspend fun getCats(): List<Cat>? {
        val response = catRepository.getCats()
        return response?.cats?: listOf()
    }

}