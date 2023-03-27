package com.extra.edge.test.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.extra.edge.test.repository.RocketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketWorker(val context: Context, val params: WorkerParameters) : Worker(context, params) {

    @Inject
    lateinit var repository: RocketRepository

    override fun doWork(): Result {

        Log.d("SANYAM", "WOKER CALLED")

        CoroutineScope(Dispatchers.IO).launch {
            repository.getRocketBackground()
        }

        return Result.success()

    }
}