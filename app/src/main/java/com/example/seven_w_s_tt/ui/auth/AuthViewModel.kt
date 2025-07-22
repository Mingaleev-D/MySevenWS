package com.example.seven_w_s_tt.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seven_w_s_tt.domain.model.AuthCredentials
import com.example.seven_w_s_tt.domain.usecase.AuthUseCase
import com.example.seven_w_s_tt.utils.SimpleResponse
import com.example.seven_w_s_tt.utils.onError
import com.example.seven_w_s_tt.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
     private val authUseCase: AuthUseCase
) : ViewModel() {

     // Изменено с 'login' на 'email'
     var email by mutableStateOf("") // Установите начальное значение, как на скриншоте
          private set

     var password by mutableStateOf("")
          private set

     // Новое поле для подтверждения пароля
     var confirmPassword by mutableStateOf("")
          private set

     private val _authResult = MutableSharedFlow<SimpleResponse<String>>()
     val authResult = _authResult.asSharedFlow()

     // Изменено с 'onLoginChanged' на 'onEmailChanged'
     fun onEmailChanged(newEmail: String) {
          email = newEmail
     }

     fun onPasswordChanged(newPassword: String) {
          password = newPassword
     }

     // Новая функция для изменения поля подтверждения пароля
     fun onConfirmPasswordChanged(newConfirmPassword: String) {
          confirmPassword = newConfirmPassword
     }

     fun register() {
          viewModelScope.launch {
               if (password != confirmPassword) {
                    _authResult.emit(SimpleResponse.Error("Пароли не совпадают"))
                    return@launch
               }

               _authResult.emit(SimpleResponse.Loading)

               val result = authUseCase.registerInv(AuthCredentials(email, password))

               result
                    .onSuccess { _authResult.emit(SimpleResponse.Success("Регистрация успешна")) }
                    .onError { _authResult.emit(SimpleResponse.Error(it)) }
          }
     }

     fun login() {
          viewModelScope.launch {
               _authResult.emit(SimpleResponse.Loading)

               val result = authUseCase.loginInv(AuthCredentials(email, password))

               result
                    .onSuccess { _authResult.emit(SimpleResponse.Success("Вход успешен")) }
                    .onError { _authResult.emit(SimpleResponse.Error(it)) }
          }
     }
}