package com.example.seven_w_s_tt.ui.coffee_list.component

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seven_w_s_tt.ui.theme.CardColor
import com.example.seven_w_s_tt.ui.theme.TextColor
import kotlin.math.roundToInt

@Composable
fun CafeItem(
     name: String,
     distanceInMeters: Float,
     onClick: () -> Unit
) {
     Card(
          modifier = Modifier
               .fillMaxWidth()
               .height(71.dp)
               .padding(vertical = 4.dp)
               .clickable(onClick = onClick),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
          colors = CardDefaults.cardColors(containerColor = CardColor)
     ) {
          Column(modifier = Modifier.padding(12.dp)) {
               Text(
                    text = name,
                    fontSize = 21.sp,
                    color = TextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp),
               )
               val distanceText = if (distanceInMeters >= 1000) {
                    "${(distanceInMeters / 1000).roundToInt()} км от вас"
               } else {
                    "${distanceInMeters.roundToInt()} м от вас"
               }
               Text(
                    text = distanceText,
                    fontSize = 14.sp,
                    color = Color(0xffAF9479),
                    style = MaterialTheme.typography.bodySmall
               )
          }
     }
}

fun calculateDistance(
     startLat: Double,
     startLon: Double,
     endLat: Double,
     endLon: Double
): Float {
     val result = FloatArray(1)
     Location.distanceBetween(startLat, startLon, endLat, endLon, result)
     return result[0]
}