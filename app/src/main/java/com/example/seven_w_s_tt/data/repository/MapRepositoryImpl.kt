package com.example.seven_w_s_tt.data.repository

import com.example.seven_w_s_tt.data.mappers.toDomain
import com.example.seven_w_s_tt.data.remote.ApiService
import com.example.seven_w_s_tt.domain.model.MapObject
import com.example.seven_w_s_tt.domain.repository.MapRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import com.example.seven_w_s_tt.utils.toAppError
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : MapRepository {

     override suspend fun getMapObjects(): SimpleResponse<List<MapObject>> {
          return try {
               val response = apiService.getMapObjects()
               val domainObjects = response.map { it.toDomain() }
               SimpleResponse.Success(domainObjects)
          } catch (e: Exception) {
               SimpleResponse.Error(e.toAppError().message)
          }
     }
}