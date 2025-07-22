package com.example.seven_w_s_tt.ui.menu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.seven_w_s_tt.R
import com.example.seven_w_s_tt.domain.model.MenuItem
import com.example.seven_w_s_tt.domain.model.mockMenuItems
import com.example.seven_w_s_tt.ui.theme.BtnColor
import com.example.seven_w_s_tt.ui.theme.CardColor
import com.example.seven_w_s_tt.ui.theme.Seven_W_S_ttTheme
import com.example.seven_w_s_tt.ui.theme.TextColor

@Composable
fun MenuCard(
     item: MenuItem,
     count: Int,
     onPlus: () -> Unit,
     onMinus: () -> Unit
) {
     Card(
          modifier = Modifier
               .fillMaxWidth()
               .aspectRatio(0.75f),
          elevation = CardDefaults.cardElevation(4.dp),
          colors = CardDefaults.cardColors(
               containerColor = Color.White
          )

     ) {
          Column(
               modifier = Modifier
                    .fillMaxSize(),
                   // .padding(8.dp),
               verticalArrangement = Arrangement.SpaceBetween,
               horizontalAlignment = Alignment.Start
          ) {

               AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                         .data(item.imageURL)
                         .crossfade(true)
                         .build(),
                    contentDescription = item.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                         .fillMaxWidth()
                         .height(120.dp),
                    placeholder = painterResource(R.drawable.demo),
                    error = painterResource(R.drawable.demo)
               )

               Spacer(modifier = Modifier.height(4.dp))
               Text(
                    item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    color = TextColor,
                    modifier = Modifier.padding(horizontal = 10.dp),
               ) // Название

               Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
               ) {
                    Text(
                         "${item.price} руб",
                         fontSize = 14.sp,
                         color = TextColor,
                         fontWeight = FontWeight.Bold
                    ) //

                    IconButton(onClick = onMinus) {
                         Icon(
                              Icons.Default.Remove,
                              contentDescription = "Уменьшить",
                              tint = CardColor
                         )
                    }
                    Text(
                         "$count",
                         fontSize = 16.sp,
                         fontWeight = FontWeight.Bold,
                         color = BtnColor,
                    ) // Текущее количество
                    IconButton(onClick = onPlus) {
                         Icon(
                              Icons.Default.Add,
                              contentDescription = "Увеличить",
                              tint = CardColor
                         )
                    }
               }
          }
     }
}

@Preview(showBackground = true)
@Composable
private fun MenuCardPreview() {
     Seven_W_S_ttTheme {
          MenuCard(
               item = mockMenuItems[1],
               count = 1,
               onPlus = TODO(),
               onMinus = TODO(),
          )

     }
}