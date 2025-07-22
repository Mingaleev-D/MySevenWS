package com.example.seven_w_s_tt.domain.usecase


import com.example.seven_w_s_tt.domain.model.MapObject
import com.example.seven_w_s_tt.domain.repository.MapRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import javax.inject.Inject

class GetMapObjectsUseCase @Inject constructor(
     private val repository: MapRepository
) {
     suspend operator fun invoke(): SimpleResponse<List<MapObject>> {
          return repository.getMapObjects()
     }
}