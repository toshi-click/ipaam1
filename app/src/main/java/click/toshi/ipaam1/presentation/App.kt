package click.toshi.ipaam1.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.support.multidex.MultiDex
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.v4.provider.FontRequest
import android.support.v7.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.messaging.FirebaseMessaging
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import click.toshi.ipaam1.R
import click.toshi.ipaam1.di.DaggerAppComponent
import click.toshi.ipaam1.di.DatabaseModule
import click.toshi.ipaam1.di.NetworkModule
import click.toshi.ipaam1.presentation.common.notification.initNotificationChannel
import click.toshi.ipaam1.service.push.processor.NewPostProcessor
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

@SuppressLint("Registered")
open class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
        setupVectorDrawable()
        setupThreeTenABP()
        setupCalligraphy()
//        setupEmoji()
//        setupNotification()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * Firebaseの初期化
     *
     * FireStoreとFirebaseMessaging
     */
    private fun setupFirebase() {
        if (FirebaseApp.getApps(this).isNotEmpty()) {
            val fireStore = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(false)
                    .build()
            fireStore.firestoreSettings = settings
            // push notification for new feed
            FirebaseMessaging.getInstance().subscribeToTopic(NewPostProcessor.TOPIC)
        }
    }

    /**
     * ベクターファイルを扱えるようにする
     */
    private fun setupVectorDrawable() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * 日付処理系のライブラリーAndroidThreeTenを初期化する
     */
    private fun setupThreeTenABP() {
        if (!isInUnitTests()) {
            AndroidThreeTen.init(this)
        }
    }

    /**
     * カスタムフォント
     */
    private fun setupCalligraphy() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFont(R.font.notosans_medium)
                .build())
    }

    /**
     * 絵文字を使えるようにする
     */
//    private fun setupEmoji() {
//        val fontRequest = FontRequest(
//                "com.google.android.gms.fonts",
//                "com.google.android.gms",
//                "Noto Color Emoji Compat",
//                R.array.com_google_android_gms_fonts_certs)
//        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
//                .setReplaceAll(true)
//                .registerInitCallback(object : EmojiCompat.InitCallback() {
//                    override fun onInitialized() {
//                        Timber.i("EmojiCompat initialized")
//                    }
//
//                    override fun onFailed(throwable: Throwable?) {
//                        Timber.e(throwable, "EmojiCompat initialization failed")
//                    }
//                })
//        EmojiCompat.init(config)
//    }

//    private fun setupNotification() {
//        initNotificationChannel()
//    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .networkModule(NetworkModule.instance)
                .databaseModule(DatabaseModule.instance)
                .build()
    }

    protected open fun isInUnitTests() = false
}
