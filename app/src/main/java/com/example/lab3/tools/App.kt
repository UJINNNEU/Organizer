package com.example.lab3.tools

import android.app.Application
import com.yandex.mapkit.MapKitFactory


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("ce750126-3260-47c3-b0ad-00f624741488")
        MapKitFactory.initialize(this)
    }
}