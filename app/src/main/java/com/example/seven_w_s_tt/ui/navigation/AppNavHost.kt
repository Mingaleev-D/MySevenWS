package com.example.seven_w_s_tt.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.seven_w_s_tt.ui.auth.AuthViewModel
import com.example.seven_w_s_tt.ui.main_app.MainViewModel
import com.example.seven_w_s_tt.ui.auth.LoginScreen
import com.example.seven_w_s_tt.ui.auth.RegisterScreen
import com.example.seven_w_s_tt.ui.coffee_list.CoffeeListScreen
import com.example.seven_w_s_tt.ui.map.MapScreen
import com.example.seven_w_s_tt.ui.map.MapViewModel
import com.example.seven_w_s_tt.ui.menu.MenuScreen
import com.example.seven_w_s_tt.ui.menu.MenuViewModel
import com.example.seven_w_s_tt.ui.order.OrderScreen
import com.example.seven_w_s_tt.utils.Constants

@Composable
fun AppNavHost(
     navController: NavHostController,
     mainViewModel: MainViewModel
) {
     val isLoggedInNullable by mainViewModel.isLoggedIn.collectAsState(initial = false)

     val menuViewModel: MenuViewModel = hiltViewModel()
     val authViewModel: AuthViewModel = hiltViewModel()
     val mapViewModel: MapViewModel = hiltViewModel()

     val startDestination = when (isLoggedInNullable) {
          true -> Screen.CoffeeList.route
          false -> Screen.Login.route
          null -> Screen.Splash.route
     }

     NavHost(
          navController = navController,
          startDestination = startDestination
     ) {
          composable(Screen.Splash.route) {
               Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
               ) {
                    CircularProgressIndicator()
               }
          }
          composable(Screen.Register.route) {
               RegisterScreen(navController = navController, viewModel = authViewModel)
          }
          composable(Screen.Login.route) {
               LoginScreen(navController = navController, viewModel = authViewModel)
          }
          composable(Screen.Map.route) {
               MapScreen(
                    navController = navController,
                    viewModel = mapViewModel
               )
          }
          composable(Screen.CoffeeList.route) {
               val userLat = Constants.userLat
               val userLon = Constants.userLon
               CoffeeListScreen(
                    userLat = userLat,
                    userLon = userLon,
                    navController = navController,
                    viewModel = mapViewModel,
                    onMapClick = { navController.navigate(Screen.Map.route) }
               )
          }
          composable(
               route = Screen.Menu.route,
               arguments = listOf(navArgument("locationId") { type = NavType.IntType })
          ) { backStackEntry ->
               val locationId = backStackEntry.arguments?.getInt("locationId") ?: -1
               if (locationId != -1) {
                    MenuScreen(
                         locationId = locationId,
                         viewModel = menuViewModel,
                         navController = navController,
                         onProceed = {
                              navController.navigate(Screen.Order.route)
                         }
                    )
               } else {
                    // todo^
                    //Text("Ошибка: ID заведения не указан.", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
               }
          }
          composable(Screen.Order.route) {
               OrderScreen(
                    navController = navController,
                    viewModel = menuViewModel,
                    onPayClick = {
                         navController.popBackStack()
                    }
               )
          }
     }
}

sealed class Screen(val route: String) {
     object Splash : Screen("splash_screen")
     object Register : Screen("register_screen")
     object Login : Screen("login_screen")
     object Map : Screen("map_screen")
     object CoffeeList : Screen("coffee_list_screen")

     object Menu : Screen("menu_screen/{locationId}") {
          fun createRoute(locationId: Int): String = "menu_screen/$locationId"
     }

     object Order : Screen("order_screen")
}
