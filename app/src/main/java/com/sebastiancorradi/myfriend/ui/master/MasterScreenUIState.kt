package com.sebastiancorradi.myfriend.ui.master

import com.sebastiancorradi.myfriend.data.Cat

data class MasterScreenUIState(
    val cats: List<Cat> = listOf(),
    val displayingAbout: Boolean = false,
    val isLoading: Boolean = false,
    var catsLoaded: Int = 0,
    )
