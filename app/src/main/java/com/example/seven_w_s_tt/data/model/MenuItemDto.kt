package com.example.seven_w_s_tt.data.model

import com.google.gson.annotations.SerializedName

data class MenuItemDto(
     val id: Int,
     val name: String,
    // @SerializedName("image_url")
     val imageURL: String,
     val price: Int
)