package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.ui.master.MasterScreenUIState
import javax.inject.Inject

class CatsRequestedUseCase @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
) {
    suspend operator fun invoke(state: MasterScreenUIState): MasterScreenUIState {
        val cats = getCatsUseCase()
        return state.copy(cats = cats)
    }
}

