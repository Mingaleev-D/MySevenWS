package com.example.seven_w_s_tt.domain.repository

import com.example.seven_w_s_tt.domain.model.AuthCredentials
import com.example.seven_w_s_tt.domain.model.AuthData
import com.example.seven_w_s_tt.utils.SimpleResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

     suspend fun register(credentials: AuthCredentials): SimpleResponse<AuthData>
     suspend fun login(credentials: AuthCredentials): SimpleResponse<AuthData>
     fun isLoggedIn(): Flow<Boolean>
}