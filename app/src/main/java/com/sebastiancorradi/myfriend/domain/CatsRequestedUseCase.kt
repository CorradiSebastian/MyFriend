package com.sebastiancorradi.myfriend.domain

import android.util.Log
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.master.MasterScreenUIState
import javax.inject.Inject

class CatsRequestedUseCase @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
) {

    suspend operator fun invoke(state: MasterScreenUIState): MasterScreenUIState {
        val cats = getCatsUseCase(state.catsLoaded)
        val loadedCats = state.cats.toMutableList()
        loadedCats.addAll(cats)
        loadedCats.sortBy { it.internalId }
        loadedCats.reverse()
        return state.copy(cats = loadedCats, isLoading = false, catsLoaded = loadedCats.size )
    }
}

