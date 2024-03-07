package com.sebastiancorradi.myfriend.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.domain.CatsRequestedUseCase
import com.sebastiancorradi.myfriend.domain.InitDetailsUseCase
import com.sebastiancorradi.myfriend.ui.master.MasterScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var initDetailsUseCase: InitDetailsUseCase

    private val _detailsScreenUIState = MutableStateFlow(DetailsScreenUIState())
    val detailsScreenUIState: StateFlow<DetailsScreenUIState> = _detailsScreenUIState.asStateFlow()

    fun initRequested(cat: Cat) {
            _detailsScreenUIState.value = initDetailsUseCase(cat)
    }
}