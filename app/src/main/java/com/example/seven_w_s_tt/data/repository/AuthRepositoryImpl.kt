package com.example.seven_w_s_tt.data.repository

import com.example.seven_w_s_tt.data.mappers.toDomain
import com.example.seven_w_s_tt.data.mappers.toDto
import com.example.seven_w_s_tt.data.remote.ApiService
import com.example.seven_w_s_tt.data.remote.common.AuthPreferences
import com.example.seven_w_s_tt.domain.model.AuthCredentials
import com.example.seven_w_s_tt.domain.model.AuthData
import com.example.seven_w_s_tt.domain.repository.AuthRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import com.example.seven_w_s_tt.utils.toAppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
     private val authApiService: ApiService,
     private val authPreferences: AuthPreferences // Внедряем AuthPreferences
) : AuthRepository {

     override suspend fun register(credentials: AuthCredentials): SimpleResponse<AuthData> {
          return try {
               val response = authApiService.register(credentials.toDto())
               authPreferences.saveAuthToken(response.token)
               SimpleResponse.Success(response.toDomain())
          } catch (e: Exception) {
               SimpleResponse.Error(e.toAppError().message)
          }
     }

     override suspend fun login(credentials: AuthCredentials): SimpleResponse<AuthData> {
          return try {
               val response = authApiService.login(credentials.toDto())
               authPreferences.saveAuthToken(response.token)
               SimpleResponse.Success(response.toDomain())
          } catch (e: Exception) {
               SimpleResponse.Error(e.toAppError().message)
          }
     }

     override fun isLoggedIn(): Flow<Boolean> {
          return authPreferences.getAuthToken().map { it != null }
     }

     //     suspend fun register(request: AuthRequest): SimpleResponse<AuthResponse> {
     //          return withContext(Dispatchers.IO) {
     //               try {
     //                    // Retrofit бросит HttpException при кодах 4xx/5xx
     //                    val response = authApiService.register(request)
     //                    authPreferences.saveAuthToken(response.token)
     //                    SimpleResponse.Success(response)
     //               } catch (e: HttpException) {
     //                    // Обработка HTTP-ошибок
     //                    val errorBody = e.response()?.errorBody()?.string()
     //                    val parsedErrorMessage = try {
     //                         // Пытаемся распарсить сообщение об ошибке из тела ответа
     //                         Gson().fromJson(errorBody, ErrorResponse::class.java)?.message
     //                    } catch (parsingE: Exception) {
     //                         null
     //                    }
     //                    val errorMessage = parsedErrorMessage ?: when (e.code()) {
     //                         400 -> "email: NotNull, NotBlank, NotEmpty / password: NotNull, NotBlank, NotEmpty" // Обновляем сообщение
     //                         404 -> "Пользователь не существует"
     //                         // 406 -> "Такой логин уже используется" // 406 для регистрации не указан в задаче
     //                         else -> "Ошибка регистрации: ${e.code()}"
     //                    }
     //                    SimpleResponse.Error(AppError.HttpError(e.code(), errorMessage).message)
     //               } catch (e: IOException) {
     //                    // Обработка сетевых ошибок (нет интернета и т.д.)
     //                    SimpleResponse.Error(AppError.NetworkError(e).message)
     //               } catch (e: Exception) {
     //                    // Обработка других ошибок
     //                    SimpleResponse.Error(AppError.UnknownError(e).message)
     //               }
     //          }
     //     }
     //
     //     suspend fun login(request: AuthRequest): SimpleResponse<AuthResponse> {
     //          return withContext(Dispatchers.IO) {
     //               try {
     //                    val response = authApiService.login(request)
     //                    authPreferences.saveAuthToken(response.token)
     //                    SimpleResponse.Success(response)
     //               } catch (e: HttpException) {
     //                    val errorBody = e.response()?.errorBody()?.string()
     //                    val parsedErrorMessage = try {
     //                         Gson().fromJson(errorBody, ErrorResponse::class.java)?.message
     //                    } catch (parsingE: Exception) {
     //                         null
     //                    }
     //                    val errorMessage = parsedErrorMessage ?: when (e.code()) {
     //                         400 -> "email: NotNull, NotBlank, NotEmpty / password: NotNull, NotBlank, NotEmpty" // Обновляем сообщение
     //                         404 -> "Пользователь не существует"
     //                         406 -> "Такой логин уже используется"
     //                         else -> "Ошибка входа: ${e.code()}"
     //                    }
     //                    SimpleResponse.Error(AppError.HttpError(e.code(), errorMessage).message)
     //               } catch (e: IOException) {
     //                    SimpleResponse.Error(AppError.NetworkError(e).message)
     //               } catch (e: Exception) {
     //                    SimpleResponse.Error(AppError.UnknownError(e).message)
     //               }
     //          }
     //     }
     //
     //     fun isLoggedIn(): Flow<Boolean> {
     //          return authPreferences.getAuthToken().map { it != null }
     //     }
     //
     //     suspend fun logout(): SimpleResponse<Unit> {
     //          return try {
     //               authPreferences.clearAuthToken()
     //               SimpleResponse.Success(Unit)
     //          } catch (e: Exception) {
     //               SimpleResponse.Error(AppError.UnknownError(e).message)
     //          }
     //     }
}
