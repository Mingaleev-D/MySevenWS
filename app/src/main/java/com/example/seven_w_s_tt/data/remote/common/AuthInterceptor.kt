package com.example.seven_w_s_tt.data.remote.common


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
     private val authPreferences: AuthPreferences
) : Interceptor {

     override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
          val originalRequest = chain.request()
          val token = runBlocking {
               authPreferences.getAuthToken().first() // Синхронно получаем токен
          }

          val requestBuilder = originalRequest.newBuilder()

          token?.let {
               requestBuilder.header("Authorization", "Bearer $it")
          }

          val request = requestBuilder.build()
          return chain.proceed(request)
     }
}