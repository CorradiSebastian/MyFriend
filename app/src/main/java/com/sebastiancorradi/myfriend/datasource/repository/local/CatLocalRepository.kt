package com.sebastiancorradi.myfriend.datasource.repository.local

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.data.CatsResponse


class CatLocalRepository: ICatLocalRepository {
    override suspend fun getCats(startFrom: Int): CatsResponse {
        val response = CatsResponse( listOf(
            Cat(id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perrito malvado"), size = 22442, mimetype = "image/jpeg"),
            Cat(id = "PYDRuvU70sbgja",
                tags = listOf("gato", "perrito malvado"),
                size = 22554,
                mimetype = "",
                )))
        return response
    }

}