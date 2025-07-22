package com.example.seven_w_s_tt.ui.map

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seven_w_s_tt.ui.componets.CenterAlignedTopAppBarComponent
import com.example.seven_w_s_tt.ui.map.components.YandexMapCompose
import com.example.seven_w_s_tt.utils.onError
import com.example.seven_w_s_tt.utils.onLoading
import com.example.seven_w_s_tt.utils.onSuccess

@Composable
fun MapScreen(
     navController: NavController,
     viewModel: MapViewModel,
) {
     val context = LocalContext.current
     val mapObjectsState by viewModel.mapObjectsState.collectAsState()

     LaunchedEffect(mapObjectsState) {
          mapObjectsState.onError { message ->
               Toast.makeText(context, message, Toast.LENGTH_LONG).show()
          }
     }

     Scaffold(
          topBar = {
               CenterAlignedTopAppBarComponent(
                    tittle = "Карта",
                    onClick = {navController.popBackStack()}
               )
          }
     ) { paddingValue ->
          Box(modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues = paddingValue)) {
               mapObjectsState.onSuccess { objects ->
                    YandexMapCompose(
                         modifier = Modifier.fillMaxSize(),
                         mapObjects = objects
                    )
               }.onLoading {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
               }.onError {
                    Text(
                         text = "Ошибка загрузки карты: $it",
                         modifier = Modifier.align(Alignment.Center)
                    )
                    Button(
                         onClick = { viewModel.loadMapObjects() },
                         modifier = Modifier
                              .align(Alignment.BottomCenter)
                              .padding(16.dp)
                    ) {
                         Text("Повторить загрузку")
                    }
               }
          }
     }
}
