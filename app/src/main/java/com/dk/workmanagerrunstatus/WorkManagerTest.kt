package com.dk.workmanagerrunstatus

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class WorkManagerTest(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object{
        const val statusPercent = "%"
    }

    override suspend fun doWork(): Result {

        for (i in 1..20) {
            val result = workDataOf(statusPercent to i*5)
            setProgress(result)
            delay(1000)
        }

        return Result.success()
    }

}