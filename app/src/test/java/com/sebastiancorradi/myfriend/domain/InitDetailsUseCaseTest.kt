package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.details.DetailsScreenUIState
import com.sebastiancorradi.myfriend.ui.master.MasterScreenUIState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class InitDetailsUseCaseTest {
    @Test
    fun initDetailsUseCaseTest() {
        val cat = Cat(id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perrito malvado"), size = 22442, mimetype = "image/jpeg")
        val initDetailsUseCase = InitDetailsUseCase()
        val detailsScreenUIState = initDetailsUseCase(cat)
        val expectedDetailScreenUIState = DetailsScreenUIState(cat)

        Assert.assertEquals(expectedDetailScreenUIState, detailsScreenUIState)

    }
}