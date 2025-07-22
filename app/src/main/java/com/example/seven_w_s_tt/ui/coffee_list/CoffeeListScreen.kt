package com.example.seven_w_s_tt.ui.coffee_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.seven_w_s_tt.ui.coffee_list.component.ButtonComponents
import com.example.seven_w_s_tt.ui.coffee_list.component.CafeItem
import com.example.seven_w_s_tt.ui.coffee_list.component.calculateDistance
import com.example.seven_w_s_tt.ui.componets.CenterAlignedTopAppBarComponent
import com.example.seven_w_s_tt.ui.map.MapViewModel
import com.example.seven_w_s_tt.ui.navigation.Screen
import com.example.seven_w_s_tt.utils.SimpleResponse

@Composable
fun CoffeeListScreen(
     viewModel: MapViewModel,
     userLat: Double,
     userLon: Double,
     navController: NavHostController,
     onMapClick: () -> Unit
) {
     val mapObjectsState by viewModel.mapObjectsState.collectAsState()

     Scaffold(
          topBar = {
               CenterAlignedTopAppBarComponent(
                    tittle = "Ближайшие кофейни",
                    onClick = { navController.navigate(Screen.Login.route) }
               )
          }
     ) { paddingValues ->
          Column(
               modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
          ) {
               Spacer(modifier = Modifier.height(16.dp))
               when (val state = mapObjectsState) {
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
                                        text = "Ошибка: ${state.message}",
                                        color = MaterialTheme.colorScheme.error
                                   )
                                   Spacer(modifier = Modifier.height(8.dp))
                                   Button(onClick = { viewModel.loadMapObjects() }) {
                                        Text("Повторить загрузку")
                                   }
                              }
                         }
                    }

                    is SimpleResponse.Success -> {
                         val cafes = state.data

                         LazyColumn(modifier = Modifier.weight(1f)) {
                              items(cafes) { cafe ->
                                   val distance = calculateDistance(
                                        userLat,
                                        userLon,
                                        cafe.point.latitude,
                                        cafe.point.longitude
                                   )
                                   CafeItem(
                                        name = cafe.name,
                                        distanceInMeters = distance,
                                        onClick = {
                                             navController.navigate(Screen.Menu.createRoute(cafe.id))
                                        }
                                   )
                              }
                         }

                         ButtonComponents(
                              textt = "На карте",
                              onClick = onMapClick,
                         )

                    }
               }
          }
     }
}
