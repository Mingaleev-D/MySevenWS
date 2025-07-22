package com.example.seven_w_s_tt.domain.repository

import com.example.seven_w_s_tt.domain.model.MapObject
import com.example.seven_w_s_tt.utils.SimpleResponse

interface MapRepository {
     suspend fun getMapObjects(): SimpleResponse<List<MapObject>>
}