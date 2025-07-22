package com.example.seven_w_s_tt.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.ui.coffee_list.component.ButtonComponents
import com.example.seven_w_s_tt.ui.componets.CenterAlignedTopAppBarComponent
import com.example.seven_w_s_tt.ui.menu.component.MenuCard
import com.example.seven_w_s_tt.utils.SimpleResponse

@Composable
fun MenuScreen(
     navController: NavController,
     locationId: Int,
     viewModel: MenuViewModel,
     onProceed: () -> Unit
) {

     val state by viewModel.menuItems
     val cart = viewModel.cart


     LaunchedEffect(locationId) {
          viewModel.loadMenu(locationId)
     }

     Scaffold(
          topBar = {
               CenterAlignedTopAppBarComponent(
                    tittle = "Меню",
                    onClick = { navController.popBackStack() }
               )
          }
     ) { paddingValues ->
          Column(
               modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
          ) {

               Spacer(modifier = Modifier.height(16.dp))
               when (val menuState = state) {
                    is SimpleResponse.Loading -> {
                         Box(
                              modifier = Modifier.fillMaxSize(),
                              contentAlignment = Alignment.Center
                         ) {
                              CircularProgressIndicator()
                         }
                    }

                    is SimpleResponse.Error -> {
                         Box(
                              modifier = Modifier.fillMaxSize(),
                              contentAlignment = Alignment.Center
                         ) {
                              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                   Text(
                                        "Ошибка загрузки меню: ${menuState.message}",
                                        color = MaterialTheme.colorScheme.error
                                   )
                                   Spacer(modifier = Modifier.height(8.dp))
                                   Button(onClick = { viewModel.loadMenu(locationId) }) {
                                        Text("Повторить загрузку")
                                   }
                              }
                         }
                    }

                    is SimpleResponse.Success -> {

                         val items = menuState.data
                         if (items.isEmpty()) {
                              Box(
                                   modifier = Modifier.fillMaxSize(),
                                   contentAlignment = Alignment.Center
                              ) {
                                   Text("В этом заведении пока нет меню.")
                              }
                         } else {
                              LazyVerticalGrid(
                                   columns = GridCells.Fixed(2),
                                   //modifier = Modifier.weight(1f),
                                   contentPadding = PaddingValues(8.dp),
                                   verticalArrangement = Arrangement.spacedBy(8.dp),
                                   horizontalArrangement = Arrangement.spacedBy(8.dp)
                              ) {
                                   items(items) { item ->
                                        MenuCard(
                                             item = item,
                                             count = cart[item.id] ?: 0,
                                             onPlus = { viewModel.increment(item.id) },
                                             onMinus = { viewModel.decrement(item.id) }
                                        )
                                   }
                              }
                         }
                         Spacer(modifier = Modifier.weight(1f))

                         ButtonComponents(
                              textt = "Перейти к оплате",
                              onClick = onProceed,
                         )
                    }
               }
          }
     }
}



