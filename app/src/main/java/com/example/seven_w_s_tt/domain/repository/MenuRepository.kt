package com.example.seven_w_s_tt.domain.repository

import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.utils.SimpleResponse

interface MenuRepository {
     suspend fun getMenu(locationId: Int): SimpleResponse<List<MenuItem>>
}