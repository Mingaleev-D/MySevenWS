package com.example.seven_w_s_tt.domain.usecase

import com.example.seven_w_s_tt.domain.model.AuthCredentials
import com.example.seven_w_s_tt.domain.model.AuthData
import com.example.seven_w_s_tt.domain.repository.AuthRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
     private val repository: AuthRepository
) {

     suspend fun registerInv(credentials: AuthCredentials): SimpleResponse<AuthData> {
          return repository.register(credentials)
     }

     suspend fun loginInv(credentials: AuthCredentials): SimpleResponse<AuthData> {
          return repository.login(credentials)
     }

     operator fun invoke(): Flow<Boolean> = repository.isLoggedIn()

}