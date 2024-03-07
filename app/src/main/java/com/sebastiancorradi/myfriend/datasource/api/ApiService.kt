package com.sebastiancorradi.myfriend.datasource.api

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.data.CatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //@GET("cats?skip=0&limit=10")
    @GET("cats")
    suspend fun getCats(@Query("skip") skip: Int = 0, @Query("limit") limit: Int = 10): List<Cat>?
}