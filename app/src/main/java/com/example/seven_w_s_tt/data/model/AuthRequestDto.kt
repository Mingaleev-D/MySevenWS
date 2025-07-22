package com.example.seven_w_s_tt.data.model

import com.google.gson.annotations.SerializedName


data class AuthRequestDto(
     val login: String,
     val password: String
)


data class AuthResponseDto(
     val token: String,
     val tokenLifeTime: Long
)


data class ErrorResponseDto(
     @SerializedName("message")
     val message: String? = null
)
/*
// Модель для запроса регистрации/входа
data class AuthRequest(
     val login: String,
     val password: String
)

// Модель для ответа успешной авторизации/регистрации
data class AuthResponse(
     val token: String,
     val tokenLifeTime: Long // Используем Long, если это время в миллисекундах
)

// Модель для ошибок API
data class ErrorResponse(
     // В зависимости от API, может быть просто строка или объект с полем "message"
     @SerializedName("message") // Если ошибка приходит как объект с полем "message"
     val message: String? = null
)
 */