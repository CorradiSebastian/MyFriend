package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.details.DetailsScreenUIState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCatsUseCaseTest {
    @Test
    fun getCatsUseCaseTest() = runTest{
        val getCatsUseCase = GetCatsUseCase(FakeCatDataSource())
        val cats = getCatsUseCase(0)
        val expectedCats = FakeCatDataSource().getCats(0)

        Assert.assertEquals(expectedCats?.size, cats.size)
        for (i in 0..expectedCats!!.lastIndex) {
            Assert.assertEquals(expectedCats.get(i), cats.get(i))
        }

    }
}