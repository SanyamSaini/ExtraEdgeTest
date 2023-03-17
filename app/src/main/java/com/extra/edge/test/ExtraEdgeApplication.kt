package com.extra.edge.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExtraEdgeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}