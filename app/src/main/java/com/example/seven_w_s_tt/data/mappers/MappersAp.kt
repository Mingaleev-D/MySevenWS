package com.example.seven_w_s_tt.data.mappers

import com.example.seven_w_s_tt.data.model.AuthRequestDto
import com.example.seven_w_s_tt.data.model.AuthResponseDto
import com.example.seven_w_s_tt.data.model.ErrorResponseDto
import com.example.seven_w_s_tt.data.model.GeoPointDto
import com.example.seven_w_s_tt.data.model.MapObjectDto
import com.example.seven_w_s_tt.data.model.MenuItemDto
import com.example.seven_w_s_tt.domain.model.AuthCredentials
import com.example.seven_w_s_tt.domain.model.AuthData
import com.example.seven_w_s_tt.domain.model.AuthError
import com.example.seven_w_s_tt.domain.model.GeoPoint
import com.example.seven_w_s_tt.domain.model.MapObject
import com.example.seven_w_s_tt.domain.model.MenuItem

// -- auth ---
fun AuthRequestDto.toDomain(): AuthCredentials {
     return AuthCredentials(
          email = login,
          password = password
     )
}

fun AuthCredentials.toDto(): AuthRequestDto {
     return AuthRequestDto(
          login = email,
          password = password
     )
}

fun AuthResponseDto.toDomain(): AuthData {
     return AuthData(
          token = token,
          tokenLifeTime = tokenLifeTime
     )
}

// -- point --
fun ErrorResponseDto.toDomain(): AuthError {
     return AuthError(
          message = message ?: "Неизвестная ошибка"
     )
}


fun GeoPointDto.toDomain(): GeoPoint {
     return GeoPoint(
          latitude = latitude,
          longitude = longitude
     )
}

fun GeoPoint.toDto(): GeoPointDto {
     return GeoPointDto(
          latitude = latitude,
          longitude = longitude
     )
}

fun MapObjectDto.toDomain(): MapObject {
     return MapObject(
          id = id,
          name = name,
          point = point.toDomain()
     )
}

fun MapObject.toDto(): MapObjectDto {
     return MapObjectDto(
          id = id,
          name = name,
          point = point.toDto()
     )
}


fun MenuItemDto.toDomain(): MenuItem {
     return MenuItem(
          id = id,
          name = name,
          imageURL = imageURL,
          price = price
     )
}

fun MenuItem.toDto(): MenuItemDto {
     return MenuItemDto(
          id = id,
          name = name,
          imageURL = imageURL,
          price = price
     )
}