package com.example.seven_w_s_tt.ui.componets

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.seven_w_s_tt.ui.theme.TextColor
import com.example.seven_w_s_tt.ui.theme.TopAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarComponent(
     tittle: String,
     onClick: () -> Unit,
) {
     CenterAlignedTopAppBar(
          title = {
               Text(
                    tittle,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                         fontWeight = FontWeight.Bold,
                         fontSize = 18.sp,
                         color = TextColor
                    )
               )
          },
          colors = TopAppBarDefaults.topAppBarColors(
               containerColor = TopAppBarColor
          ),

          navigationIcon = {
               IconButton(onClick = { onClick() }) {
                    Icon(
                         Icons.AutoMirrored.Filled.ArrowBackIos,
                         contentDescription = "Назад",
                         modifier = Modifier.size(16.dp),
                    )
               }
          }
     )
}