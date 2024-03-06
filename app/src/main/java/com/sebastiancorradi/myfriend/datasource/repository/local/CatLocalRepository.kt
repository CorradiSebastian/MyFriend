package com.sebastiancorradi.myfriend.datasource.repository.local

import com.sebastiancorradi.myfriend.data.Cat


class CatLocalRepository: ICatLocalRepository {
    override fun getCats(): List<Cat> {
        return listOf(Cat(id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perrito malvado")))
    }

}