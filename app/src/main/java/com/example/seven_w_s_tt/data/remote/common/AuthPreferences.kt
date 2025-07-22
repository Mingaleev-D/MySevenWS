package com.example.seven_w_s_tt.data.remote.common

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

// Создаем DataStore для хранения настроек (токена)
val Context.authDataStore by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthPreferences @Inject constructor(@ApplicationContext private val context: Context) {

     private object PreferencesKeys {
          val AUTH_TOKEN = stringPreferencesKey("auth_token")
     }

     // Сохранить токен
     suspend fun saveAuthToken(token: String) {
          context.authDataStore.edit { preferences ->
               preferences[PreferencesKeys.AUTH_TOKEN] = token
          }
     }

     // Получить токен
     fun getAuthToken(): Flow<String?> {
          return context.authDataStore.data.map { preferences ->
               preferences[PreferencesKeys.AUTH_TOKEN]
          }
     }

     // Очистить токен (например, при выходе)
     suspend fun clearAuthToken() {
          context.authDataStore.edit { preferences ->
               preferences.remove(PreferencesKeys.AUTH_TOKEN)
          }
     }
}