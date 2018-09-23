package click.toshi.ipaam1

import android.app.Application

interface AppLifecycleCallbacks {

  fun onCreate(application: Application)

  fun onTerminate(application: Application)
}
