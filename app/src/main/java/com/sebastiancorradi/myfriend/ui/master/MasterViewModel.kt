package com.sebastiancorradi.myfriend.ui.master

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastiancorradi.myfriend.domain.CatsRequestedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var catsRequestedUseCase: CatsRequestedUseCase

    private val _masterScreenUIState = MutableStateFlow(MasterScreenUIState())
    val masterScreenUIState: StateFlow<MasterScreenUIState> = _masterScreenUIState.asStateFlow()

    fun catsRequested():Boolean {
        _masterScreenUIState.value = _masterScreenUIState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _masterScreenUIState.emit(_masterScreenUIState.value)
            _masterScreenUIState.value = catsRequestedUseCase(_masterScreenUIState.value)
        }
        return true
    }


}