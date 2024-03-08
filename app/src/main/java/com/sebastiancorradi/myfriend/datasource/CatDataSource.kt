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
        Log.e("Sebas", "intento traer de la BD, startFrom: $startFrom")
        if (response?.success == true && response.cats.isNotEmpty()) {
            Log.e("Sebas", "trajo de la BD")
            Log.e("Sebas", "trajo ultimo: ${response.cats.get(response.cats.size -1)}")
            Log.e("Sebas", "trajo: ${response.cats.size}")



            return response.cats
        } else {
            Log.e("Sebas", "intenta traer del backend, startFrom: $startFrom")
            val response = catRemoteRepository.getCats(startFrom)
            if (response?.success == true) {
                Log.e("Sebas", "trajo del backend")
                Log.e("Sebas", "intentara grabar en la base")
                saveCatsUseCase(response.cats)
                Log.e("Sebas", "intento grabar en la base")
                return response.cats ?: listOf()
            } else {
                Log.e("Sebas", "traera local")
                return catLocalRepository.getCats()?.cats

            }
        }
    }
}