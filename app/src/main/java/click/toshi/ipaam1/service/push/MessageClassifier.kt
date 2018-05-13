package click.toshi.ipaam1.service.push

import com.google.firebase.messaging.RemoteMessage
import dagger.Lazy
import click.toshi.ipaam1.service.push.processor.DefaultProcessor
import click.toshi.ipaam1.service.push.processor.MessageProcessor
import click.toshi.ipaam1.service.push.processor.NewPostProcessor
import javax.inject.Inject

class MessageClassifier @Inject constructor(
        private val postProcessor: Lazy<NewPostProcessor>,
        private val defaultProcessor: Lazy<DefaultProcessor>) {
    fun classify(remoteMessage: RemoteMessage): MessageProcessor {
        return when (remoteMessage.from) {
            NewPostProcessor.FCM_FROM -> {
                postProcessor.get()
            }
            else -> {
                defaultProcessor.get()
            }
        }
    }
}
