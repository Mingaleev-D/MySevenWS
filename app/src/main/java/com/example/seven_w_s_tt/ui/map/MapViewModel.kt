package com.example.seven_w_s_tt.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seven_w_s_tt.domain.model.MapObject
import com.example.seven_w_s_tt.domain.usecase.GetMapObjectsUseCase
import com.example.seven_w_s_tt.utils.SimpleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
     private val getMapObjectsUseCase: GetMapObjectsUseCase
) : ViewModel() {
     private val _mapObjectsState =
          MutableStateFlow<SimpleResponse<List<MapObject>>>(SimpleResponse.Loading)
     val mapObjectsState: StateFlow<SimpleResponse<List<MapObject>>> = _mapObjectsState

     init {
          loadMapObjects()
     }

     fun loadMapObjects() {
          viewModelScope.launch {
               _mapObjectsState.value = SimpleResponse.Loading
               val result = getMapObjectsUseCase()
               _mapObjectsState.value = result
          }
     }
}