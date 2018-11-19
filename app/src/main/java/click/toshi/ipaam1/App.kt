//package click.toshi.ipaam1
//
//import dagger.android.support.DaggerApplication
//import click.toshi.ipaam1.di.DaggerAppComponent
//import click.toshi.ipaam1.di.applyAutoInjector
//import javax.inject.Inject
//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
//
//
//
//class App : DaggerApplication() {
//
//  @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks
//
//  override fun applicationInjector() = DaggerAppComponent.builder()
//      .application(this)
//      .build()
//
//  override fun onCreate() {
//    super.onCreate()
//    applyAutoInjector()
//    Fabric.with(this, Crashlytics())
//    appLifecycleCallbacks.onCreate(this)
//  }
//
//  override fun onTerminate() {
//    appLifecycleCallbacks.onTerminate(this)
//    super.onTerminate()
//  }
//
//}
