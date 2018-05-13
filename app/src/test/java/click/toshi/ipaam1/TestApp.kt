package click.toshi.ipaam1

import com.chibatching.kotpref.Kotpref
import click.toshi.ipaam1.presentation.App
import org.robolectric.RuntimeEnvironment

class TestApp : App() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(RuntimeEnvironment.application)
    }

    override fun isInUnitTests(): Boolean {
        return true
    }
}
