package com.example.seven_w_s_tt.data.remote

import com.example.seven_w_s_tt.data.model.AuthRequestDto
import com.example.seven_w_s_tt.data.model.AuthResponseDto
import com.example.seven_w_s_tt.data.model.MapObjectDto
import com.example.seven_w_s_tt.data.model.MenuItemDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

     @POST("auth/register")
     suspend fun register(@Body request: AuthRequestDto): AuthResponseDto

     @POST("auth/login")
     suspend fun login(@Body request: AuthRequestDto): AuthResponseDto

     @GET("locations")
     suspend fun getMapObjects(): List<MapObjectDto>

     @GET("location/{id}/menu")
     suspend fun getMenu(
          @Path("id") locationId: Int
     ): List<MenuItemDto>

}
