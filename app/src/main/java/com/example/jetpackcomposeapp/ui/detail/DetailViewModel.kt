package com.example.jetpackcomposeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.data.OriginoteList
import com.example.jetpackcomposeapp.data.OriginoteRepository
import com.example.jetpackcomposeapp.ui.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: OriginoteRepository): ViewModel() {
    private val _state: MutableStateFlow<State<OriginoteList>> = MutableStateFlow(State.Loading)

    val uiState: StateFlow<State<OriginoteList>> get() = _state

    fun getOriginoteId(originoteId: Long) {
        viewModelScope.launch {
            _state.value = State.Loading
            _state.value = State.Success(repository.getDataById(originoteId))
        }
    }
}
