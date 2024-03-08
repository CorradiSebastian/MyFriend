package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.ui.master.MasterScreenUIState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CatsRequestedUseCaseTest {
    @Test
    fun catsRequestedUseCaseTest() = runTest(){
        val catsRequestedUseCase = CatsRequestedUseCase(FakeGetCatsUseCase())
        var state = MasterScreenUIState()
        state = catsRequestedUseCase.invoke(state)
        val cats = FakeCatDataSource().getFakeCats()
        Assert.assertEquals(cats.size, state.catsLoaded,)
        Assert.assertEquals(cats.size, state.cats.size,)
        Assert.assertEquals(cats.get(0).size, state.cats.get(0).size)
        for (i in 0..cats.lastIndex)
        {
            Assert.assertEquals(cats.get(i), state.cats.get(i))
        }

    }
}