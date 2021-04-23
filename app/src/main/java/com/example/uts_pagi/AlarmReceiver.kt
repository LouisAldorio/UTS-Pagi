package com.example.uts_pagi

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class ScheduledAlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val NotificationId = 20000
        val channelId = intent.getStringExtra(ALARM_MANAGER_CHANNELID)
        val name = intent.getStringExtra(ALARM_MANAGER_CHANNELID)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val list = mutableListOf<NotificationChannelGroup>()
        list.add(
            NotificationChannelGroup(
                "App Activity",
                "App Activity"
            )
        )

        val NotifyChannel = NotificationChannel(channelId, name, importance)
        NotifyChannel.group = "App Activity"

        val contentView = RemoteViews(context.packageName, R.layout.custom_notification_layout)
        contentView.setTextViewText(R.id.sub_title,"Car Arrive!")
        contentView.setTextViewText(R.id.notif_title,intent?.getStringExtra(EXTRA_PESAN))

        val Builder = NotificationCompat.Builder(context!!,channelId!!)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContent(contentView)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        var NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        NotificationManager.createNotificationChannelGroups(list)
        NotificationManager.createNotificationChannel(NotifyChannel)
        NotificationManager.notify(NotificationId,Builder.build())
    }
}