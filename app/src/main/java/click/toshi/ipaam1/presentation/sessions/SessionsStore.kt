package click.toshi.ipaam1.presentation.sessions

import android.arch.lifecycle.LiveData
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.presentation.action.SessionChangedAction
import click.toshi.ipaam1.presentation.action.SessionLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.ext.toLiveData
import kotlinx.coroutines.experimental.channels.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionsStore @Inject constructor(
        private val dispatcher: Dispatcher
) {
    val sessions: LiveData<List<Session>> by lazy {
        dispatcher.asChannel<SessionChangedAction>()
                .map { it.sessions }
                .toLiveData()
    }

    val loadingState: LiveData<LoadState> by lazy {
        dispatcher.asChannel<SessionLoadStateChangeAction>()
                .map { it.loadState }
                .toLiveData()
    }
}
