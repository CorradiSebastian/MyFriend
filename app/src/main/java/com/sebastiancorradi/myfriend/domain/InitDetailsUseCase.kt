package com.sebastiancorradi.myfriend.domain

import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.details.DetailsScreenUIState
import javax.inject.Inject

class InitDetailsUseCase @Inject constructor() {
    operator fun invoke(cat: Cat): DetailsScreenUIState {
        return DetailsScreenUIState(cat)
    }
}