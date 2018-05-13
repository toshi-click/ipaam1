package click.toshi.ipaam1.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry

class TestLifecycleOwner : LifecycleOwner {

    private val lifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycle

    fun handleEvent(event: Lifecycle.Event) {
        lifecycle.handleLifecycleEvent(event)
    }
}
