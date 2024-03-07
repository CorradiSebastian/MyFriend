package com.sebastiancorradi.myfriend.datasource.repository.local

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.data.CatsResponse


class CatLocalRepository: ICatLocalRepository {
    override suspend fun getCats(): CatsResponse {
        val response = CatsResponse( listOf(
            Cat(id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perrito malvado")),
            Cat(id = "",
                tags = listOf("gato", "perrito malvado"),
                owner = "",
                createdAt = "",
                upatedAt = "",
                ))
        )
        return response
    }

}