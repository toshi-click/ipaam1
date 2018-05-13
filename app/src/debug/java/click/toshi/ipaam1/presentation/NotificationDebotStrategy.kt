package click.toshi.ipaam1.presentation

import android.app.Activity
import android.content.Context
import com.tomoima.debot.strategy.DebotStrategy
import click.toshi.ipaam1.R
import click.toshi.ipaam1.presentation.common.notification.NotificationBroadcastReceiver
import click.toshi.ipaam1.presentation.common.notification.NotificationContent
import click.toshi.ipaam1.util.ext.toReadableDateTimeString
import java.util.Date

class NotificationDebotStrategy : DebotStrategy() {

    override fun startAction(activity: Activity) {
        // lets send a notification!
        sendNotification(activity)
    }

    private fun sendNotification(context: Context) {
        val id = "0"
        val startTime = Date()
        val endTime = Date(startTime.time + (60 * 60 * 1000))
        val roomName = "Room 2"

        // This code is copied from SessionAlarm::createAlarmIntent
        val title = context.getString(R.string.notification_title, "Notification Test")
        val displaySTime = startTime.toReadableDateTimeString()
        val displayETime = endTime.toReadableDateTimeString()
        val text = context.getString(R.string.notification_message,
                displaySTime,
                displayETime,
                roomName)
        val notificationContent = NotificationContent.FavoriteSessionStart(title, text, id)

        val intent = NotificationBroadcastReceiver.createIntent(
                context,
                notificationContent
        )

        context.sendBroadcast(intent)
    }
}
