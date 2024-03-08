package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import com.sebastiancorradi.myfriend.datasource.data.CatsResponse

class FakeCatDataSource:ICatDataSource {
    override suspend fun getCats(startFrom: Int): List<Cat>? {
        return getFakeCats()
    }

    fun getFakeCats(): List<Cat>{
        return listOf(Cat(internalId = 1, id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perrito malvado"), size = 22442, mimetype = "image/jpeg"),
            Cat(internalId = 2, id = "PYDRuvU70sbgja",
                tags = listOf("gato", "perrito malvado"),
                size = 22554,
                mimetype = "",
            )).sortedBy { it.internalId }.reversed()
    }
}