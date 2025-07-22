package com.example.seven_w_s_tt.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.seven_w_s_tt.ui.coffee_list.component.ButtonComponents
import com.example.seven_w_s_tt.ui.componets.CenterAlignedTopAppBarComponent
import com.example.seven_w_s_tt.ui.menu.MenuViewModel
import com.example.seven_w_s_tt.ui.theme.TextColor
import com.example.seven_w_s_tt.utils.SimpleResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
     viewModel: MenuViewModel,
     navController: NavHostController,
     onPayClick: () -> Unit
) {

     val state by viewModel.menuItems
     val allMenuItems = (state as? SimpleResponse.Success)?.data.orEmpty()
     val selectedItems = viewModel.getSelectedItems(allMenuItems)

     val totalAmount = selectedItems.sumOf { (item, quantity) ->
          item.price * quantity
     }

     Scaffold(
          topBar = {
               CenterAlignedTopAppBarComponent(
                    tittle = "Ваш заказ",
                    onClick = { navController.popBackStack() }
               )
          }
     ) { paddingValues ->
          Column(
               modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally
          ) {
               Spacer(modifier = Modifier.height(16.dp))
               if (selectedItems.isEmpty()) {
                    Box(
                         modifier = Modifier
                              .weight(1f)
                              .fillMaxWidth(),
                         contentAlignment = Alignment.Center
                    ) {
                         Text(
                              "Ваша корзина пуста. Добавьте что-нибудь из меню!",
                              textAlign = TextAlign.Center,
                              modifier = Modifier.padding(16.dp)
                         )
                    }
               } else {
                    LazyColumn() {
                         items(selectedItems) { (item, quantity) ->
                              Card(
                                   modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                   colors = CardDefaults.cardColors(
                                        containerColor = Color(
                                             0xFFF2DDBC
                                        )
                                   )
                              ) {
                                   Row(
                                        modifier = Modifier
                                             .fillMaxWidth()
                                             .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                   ) {
                                        Column {
                                             Text(item.name, fontWeight = FontWeight.Bold)
                                             Text(
                                                  "${item.price} руб",
                                                  fontSize = 12.sp,
                                                  color = Color.Gray
                                             )
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                             IconButton(onClick = { viewModel.decrement(item.id) }) {
                                                  Icon(
                                                       Icons.Default.Remove,
                                                       contentDescription = "Уменьшить"
                                                  )
                                             }
                                             Text(
                                                  "$quantity",
                                                  fontSize = 16.sp,
                                                  fontWeight = FontWeight.Bold
                                             )
                                             IconButton(onClick = { viewModel.increment(item.id) }) {
                                                  Icon(
                                                       Icons.Default.Add,
                                                       contentDescription = "Увеличить"
                                                  )
                                             }
                                        }
                                   }
                              }
                         }
                    }

               }

               // Текстовое сообщение о времени ожидания
               Box(
                    modifier = Modifier
                         .fillMaxWidth()
                         .weight(1f),
                    contentAlignment = Alignment.Center
               ) {
                    Text(
                         text = "Время ожидания заказа\n15 минут!\nСпасибо, что выбрали нас!",
                         textAlign = TextAlign.Center,
                         fontSize = 24.sp,
                         color = TextColor,
                         modifier = Modifier.padding(16.dp)
                    )
               }

               // Кнопка "Оплатить"
               Spacer(modifier = Modifier.weight(1f))

               ButtonComponents(
                    textt = "Оплатить",
                    onClick = onPayClick,
               )
          }
     }
}