package com.sebastiancorradi.myfriend.datasource

import android.util.Log
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.repository.ICatRepository
import com.sebastiancorradi.myfriend.datasource.repository.db.CatDBRepository
import com.sebastiancorradi.myfriend.datasource.repository.db.ICatDBRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.ICatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.ICatRemoteRepository
import com.sebastiancorradi.myfriend.domain.SaveCatsUseCase
import javax.inject.Inject

class CatDataSource @Inject constructor(
    private val catRemoteRepository: ICatRemoteRepository,
    private val catLocalRepository: ICatLocalRepository,
    private val catDBRepository: ICatDBRepository,
    private val saveCatsUseCase: SaveCatsUseCase,
) : ICatDataSource {
    private val pageSize = 10
    override suspend fun getCats(startFrom: Int): List<Cat>? {
        val response = catDBRepository.getCats(startFrom)
        if (response?.success == true && response.cats.isNotEmpty()) {
            return response.cats
        } else {
            val response = catRemoteRepository.getCats(startFrom)
            if (response?.success == true) {
                saveCatsUseCase(response.cats)
                return response.cats ?: listOf()
            } else {
                return catLocalRepository.getCats()?.cats

            }
        }
    }
}