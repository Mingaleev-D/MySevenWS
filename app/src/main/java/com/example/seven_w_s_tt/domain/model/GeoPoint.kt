package com.example.seven_w_s_tt.domain.model


data class GeoPoint(
     val latitude: Double,
     val longitude: Double
)


data class MapObject(
     val id: Int,
     val name: String,
     val point: GeoPoint
)
