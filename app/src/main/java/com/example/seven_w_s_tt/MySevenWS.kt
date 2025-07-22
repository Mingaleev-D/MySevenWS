package com.example.seven_w_s_tt

import android.app.Application
import com.example.seven_w_s_tt.utils.Constants
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MySevenWS : Application() {

     override fun onCreate() {
          super.onCreate()

          MapKitFactory.setApiKey(Constants.yamdexMapKit)
          MapKitFactory.initialize(this)
     }
}