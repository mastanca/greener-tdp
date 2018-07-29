package com.saantiaguilera.greener.util

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.support.v4.content.res.ResourcesCompat
import com.saantiaguilera.greener.R

class NotificationUtil {

    companion object {

        fun showNotification(context: Context, title: String, body: String) {
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_splash)
                    .setColor(ResourcesCompat.getColor(context.resources, R.color.colorPrimary, context.theme))
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setPriority(NotificationManager.IMPORTANCE_MAX)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notificationBuilder.build())
        }

    }

}