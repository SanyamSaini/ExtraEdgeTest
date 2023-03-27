package com.extra.edge.test

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.extra.edge.test.worker.RocketWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class ExtraEdgeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}