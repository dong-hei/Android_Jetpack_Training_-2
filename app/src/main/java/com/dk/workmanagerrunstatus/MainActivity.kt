package com.dk.workmanagerrunstatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.dk.workmanagerrunstatus.WorkManagerTest.Companion.statusPercent

/**
 * WorkManager가 오래걸리는 작업이 몇 퍼센트 진행되었는지 확인
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wmTest = OneTimeWorkRequestBuilder<WorkManagerTest>().build()
        WorkManager.getInstance(this).enqueue(wmTest)

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(wmTest.id)
            .observe(this, Observer { workInfo : WorkInfo ->
                val progress = workInfo?.progress
                val value = progress?.getInt(statusPercent, 0)
                if(value != 0){
                    Log.d("%(percent)", value.toString())
                }
            })

    }
}