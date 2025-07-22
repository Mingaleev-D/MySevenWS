package com.example.seven_w_s_tt.domain.usecase

import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.domain.repository.MenuRepository
import com.example.seven_w_s_tt.utils.SimpleResponse
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
     private val repository: MenuRepository
) {
     suspend operator fun invoke(locationId: Int): SimpleResponse<List<MenuItem>> {
          return repository.getMenu(locationId)
     }
}