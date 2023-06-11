package com.example.jetpackcomposeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.data.OriginoteList
import com.example.jetpackcomposeapp.data.OriginoteRepository
import com.example.jetpackcomposeapp.ui.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: OriginoteRepository): ViewModel(){

    private val _state: MutableStateFlow<State<List<OriginoteList>>> = MutableStateFlow(State.Loading)

    val state: StateFlow<State<List<OriginoteList>>> get() = _state

    fun getAllData(){
        viewModelScope.launch {
            repository.getAllData().catch {
                _state.value = State.Error(it.message.toString())
            }
                .collect{
                    dataOriginote -> _state.value = State.Success(dataOriginote)
                }
        }
    }
}