package com.example.seven_w_s_tt.ui.map.components

import android.graphics.BitmapFactory
import android.graphics.PointF
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.seven_w_s_tt.R
import com.example.seven_w_s_tt.domain.model.MapObject
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun YandexMapCompose(
     modifier: Modifier = Modifier,
     initialPoint: Point = Point(44.673, 44.673),
     initialZoom: Float = 14.0f,
     mapObjects: List<MapObject> = emptyList(),
     onMapReady: (MapView) -> Unit = {}
) {
     val context = LocalContext.current
     val mapView = remember { MapView(context) }
     var mapObjectCollection: MapObjectCollection? by remember { mutableStateOf(null) }

     val placemarkImageProvider = remember {
          ImageProvider.fromBitmap(
               BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.group_11
               )
          )
     }

     val mapObjectTapListener = remember {
          MapObjectTapListener { mapObject, _ ->
               val clickedPlacemark = mapObject as? PlacemarkMapObject
               val objectName = clickedPlacemark?.userData as? String
               Toast.makeText(context, "Название: ${objectName}", Toast.LENGTH_SHORT).show()
               true
          }
     }

     DisposableEffect(key1 = mapView) {
          onMapReady(mapView)

          MapKitFactory.getInstance()
               .onStart() // MapKitFactory должен стартовать до mapView.onStart()
          mapView.onStart() // Запускаем карту здесь, чтобы она была готова при создании AndroidView

          onDispose {
               mapView.onStop()
               MapKitFactory.getInstance().onStop()
          }
     }

     AndroidView(
          modifier = modifier,
          factory = {
               mapView.apply {
                    map.move(
                         CameraPosition(initialPoint, initialZoom, 0.0f, 0.0f),
                         Animation(Animation.Type.SMOOTH, 0F),
                         null
                    )
                    mapObjectCollection = map.mapObjects
               }
          },
          update = { view ->
               mapObjectCollection?.clear() // Очищаем старые метки перед добавлением новых

               mapObjects.forEach { appMapObject -> // Используем appMapObject, если вы используете AppMapObject в viewModel
                    val placemark: PlacemarkMapObject? = mapObjectCollection?.addPlacemark(
                         Point(appMapObject.point.latitude, appMapObject.point.longitude),
                         placemarkImageProvider
                    )
                    placemark?.userData = appMapObject.name
                    placemark?.addTapListener(mapObjectTapListener)
                    placemark?.setIconStyle(IconStyle().apply {
                         anchor = PointF(0.5f, 0.5f) // Центрируем иконку
                    })

                    // *** Добавляем название под меткой ***
                    placemark?.setText(appMapObject.name)
                    placemark?.setTextStyle(com.yandex.mapkit.map.TextStyle().apply {
                         placement =
                              com.yandex.mapkit.map.TextStyle.Placement.BOTTOM // Размещаем текст снизу
                         offset = 4f // Небольшой отступ от иконки
                         size = 12f // Размер текста
                         color = android.graphics.Color.BLACK // Цвет текста
                         outlineColor =
                              android.graphics.Color.WHITE // Цвет обводки текста (для лучшей читаемости)
                         outlineWidth = 1f // Ширина обводки
                    })
               }
          }
     )
}