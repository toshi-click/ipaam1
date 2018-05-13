package click.toshi.ipaam1.presentation.common.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class LocaleChangedBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Timber.d("LocaleChangedBroadcastReceiver.onReceive")
        if (Intent.ACTION_LOCALE_CHANGED == intent?.action) {
            context.initNotificationChannel()
        }
    }
}
