package com.example.seven_w_s_tt.data.model


data class GeoPointDto(
     val latitude: Double,
     val longitude: Double
)

// data.remote.dto.MapObjectDto.kt
data class MapObjectDto(
     val id: Int,
     val name: String,
     val point: GeoPointDto
)

//// Модель для географической точки
//data class GeoPoint(
//     val latitude: Double,
//     val longitude: Double
//)
//
//// Модель для объекта на карте
//data class MapObject(
//     val id: Int,
//     val name: String,
//     val point: GeoPoint
//)