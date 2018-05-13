package click.toshi.ipaam1

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val packageName = if (BuildConfig.DEBUG) {
            "click.toshi.ipaam1.debug"
        } else {
            "click.toshi.ipaam1"
        }
        assertEquals(packageName, appContext.packageName)
    }
}
