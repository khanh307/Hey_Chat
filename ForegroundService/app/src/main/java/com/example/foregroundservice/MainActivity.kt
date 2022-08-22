package com.example.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    btn.setOnClickListener {
        Log.e("AAA", time.currentHour.toString())
        var calender = Calendar.getInstance()
        calender.timeInMillis = System.currentTimeMillis()
        calender.set(Calendar.HOUR_OF_DAY, time.currentHour)
        calender.set(Calendar.MINUTE, time.currentMinute)
        Log.e("AAA", calender.timeInMillis.toString())
    }

    }

}