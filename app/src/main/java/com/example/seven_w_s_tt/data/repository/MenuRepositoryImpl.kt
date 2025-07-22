package com.example.seven_w_s_tt.data.repository

import com.example.seven_w_s_tt.data.mappers.toDomain
import com.example.seven_w_s_tt.data.remote.ApiService
import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.domain.repository.MenuRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import com.example.seven_w_s_tt.utils.toAppError
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : MenuRepository {

     override suspend fun getMenu(locationId: Int): SimpleResponse<List<MenuItem>> {
          return try {
               val response = apiService.getMenu(locationId = locationId)
               val domainMenu = response.map { it.toDomain() }
               SimpleResponse.Success(domainMenu)
          } catch (e: Exception) {
               SimpleResponse.Error(e.toAppError().message)
          }
     }
}

