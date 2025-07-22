package com.example.seven_w_s_tt.domain.model


data class AuthData(
     val token: String,
     val tokenLifeTime: Long
)

data class AuthCredentials(
     val email: String,
     val password: String
)

data class AuthError(
     val message: String
)