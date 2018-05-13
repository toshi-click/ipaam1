package click.toshi.ipaam1.service.push.processor

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import com.google.firebase.messaging.RemoteMessage
import click.toshi.ipaam1.R
import click.toshi.ipaam1.presentation.MainActivity
import click.toshi.ipaam1.presentation.common.notification.NotificationChannelType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewPostProcessor @Inject constructor(
        private val application: Application,
        private val notificationManager: NotificationManager) : MessageProcessor {
    override fun process(message: RemoteMessage) {
        if (message.data.isEmpty())
            return
        val title = message.data[KEY_TITLE]
        val content = message.data[KEY_CONTENT]
        val type = message.data[KEY_TYPE]
        val openPi = PendingIntent.getActivity(application,
                0,
                Intent(application, MainActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                // TODO open with announce fragment in front?
                PendingIntent.FLAG_UPDATE_CURRENT)
        val priority = when (type) {
            "notification" -> NotificationCompat.PRIORITY_HIGH
            "alert" -> NotificationCompat.PRIORITY_HIGH
            else -> NotificationCompat.PRIORITY_DEFAULT
        }
        val notification: Notification = NotificationCompat.Builder(application,
                NotificationChannelType.NEW_FEED_POST.id)
                .setStyle(
                        NotificationCompat.BigTextStyle()
                                .setBigContentTitle(title)
                                .bigText(content)
                )
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(priority)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setColor(ContextCompat.getColor(application, R.color.primary))
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(openPi)
                .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val FCM_FROM = "/topics/post"
        const val NOTIFICATION_ID = 1
        const val KEY_TITLE = "title"
        const val KEY_CONTENT = "content"
        const val KEY_TYPE = "type"
        const val TOPIC = "post"
    }
}
