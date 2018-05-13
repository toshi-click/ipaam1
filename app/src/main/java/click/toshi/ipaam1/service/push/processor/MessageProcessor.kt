package click.toshi.ipaam1.service.push.processor

import com.google.firebase.messaging.RemoteMessage

interface MessageProcessor {
    fun process(message: RemoteMessage)
}
