package com.example.seven_w_s_tt.ui.main_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.seven_w_s_tt.ui.navigation.AppNavHost
import com.example.seven_w_s_tt.ui.theme.Seven_W_S_ttTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContent {
               Seven_W_S_ttTheme {
                    Surface(
                         modifier = Modifier.fillMaxSize(),
                         color = MaterialTheme.colorScheme.background
                    ) {
                         val navController = rememberNavController()
                         val mainViewModel: MainViewModel = hiltViewModel()
                         AppNavHost(
                              navController = navController,
                              mainViewModel = mainViewModel
                         )

                    }
               }
          }
     }
}
