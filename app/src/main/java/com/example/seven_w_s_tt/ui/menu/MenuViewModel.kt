package com.example.seven_w_s_tt.ui.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.domain.usecase.GetMenuUseCase
import com.example.seven_w_s_tt.utils.SimpleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
     private val getMenuUseCase: GetMenuUseCase
) : ViewModel() {

     private val _menuItems = mutableStateOf<SimpleResponse<List<MenuItem>>>(SimpleResponse.Loading)
     val menuItems: State<SimpleResponse<List<MenuItem>>> = _menuItems

     private val _cart = mutableStateMapOf<Int, Int>()
     val cart: Map<Int, Int> = _cart

     fun loadMenu(locationId: Int) {
          viewModelScope.launch {
               _menuItems.value = SimpleResponse.Loading
               val result = getMenuUseCase(locationId)
               _menuItems.value = result
          }
     }

     fun increment(itemId: Int) {
          _cart[itemId] = (_cart[itemId] ?: 0) + 1
     }

     fun decrement(itemId: Int) {
          val current = _cart[itemId] ?: 0
          if (current > 0) {
               _cart[itemId] = current - 1
          }
     }

     fun getSelectedItems(allItems: List<MenuItem>): List<Pair<MenuItem, Int>> {
          return allItems.filter { _cart[it.id] ?: 0 > 0 }
               .map { it to (_cart[it.id] ?: 0) }
     }

     fun clearCart() {
          _cart.clear()
     }
}