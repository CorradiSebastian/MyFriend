package com.sebastiancorradi.myfriend.datasource.repository.db

import com.sebastiancorradi.myfriend.datasource.data.CatsResponse

class CatDBRepository:ICatDBRepository {
    override suspend fun getCats(startFrom: Int): CatsResponse {
        try {
            val db = AppDatabase.getDb()
            val cats = db.catDao().getAll(startFrom)
            val response = CatsResponse(cats, success = true)
            return response
        } catch (e: Exception){
            return CatsResponse(success = false, errorMessage = e.message)
        }
    }

}