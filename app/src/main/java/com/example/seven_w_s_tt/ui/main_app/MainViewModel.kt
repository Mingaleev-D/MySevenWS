package com.example.seven_w_s_tt.ui.main_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seven_w_s_tt.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     private val authUseCase: AuthUseCase
) : ViewModel() {

     // Состояние авторизации
     private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
     val isLoggedIn = _isLoggedIn.asStateFlow()

     init {
          checkLoginStatus()
     }

     private fun checkLoginStatus() {
          viewModelScope.launch {
               authUseCase.invoke().collect { loggedIn ->
                    _isLoggedIn.value = loggedIn
               }
          }
     }
}