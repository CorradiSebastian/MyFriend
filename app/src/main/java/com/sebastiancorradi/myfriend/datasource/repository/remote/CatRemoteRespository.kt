package com.sebastiancorradi.myfriend.datasource.repository.remote

import android.util.Log
import com.sebastiancorradi.myfriend.datasource.api.ApiClient
import com.sebastiancorradi.myfriend.datasource.data.CatsResponse
import retrofit2.HttpException
import java.net.SocketTimeoutException

class CatRemoteRepository:ICatRemoteRepository {
    override suspend fun getCats(startFrom: Int): CatsResponse? {
        try {
            val cats = ApiClient.apiService.getCats(startFrom, 10)
            cats?.let {
                return CatsResponse(success = true, cats = cats)
            }
            return CatsResponse(success = false)
        } catch (e: HttpException) {
            Log.e("sebas", "Exception: $e")
            return CatsResponse(errorCode = e.code(),
                errorMessage = e.message,
                success = false)
        } catch (e: SocketTimeoutException) {
            Log.e("sebas", "Exception: $e")
            return CatsResponse(
                errorCode = 400, errorMessage = e.message, success = false
            )
        }
    }
}