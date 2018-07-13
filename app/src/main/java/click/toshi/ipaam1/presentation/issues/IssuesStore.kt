package click.toshi.ipaam1.presentation.issues

import android.arch.lifecycle.LiveData
import click.toshi.ipaam1.model.Issue
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.presentation.action.IssueChangedAction
import click.toshi.ipaam1.presentation.action.IssueLoadStateChangeAction
import click.toshi.ipaam1.presentation.action.SessionChangedAction
import click.toshi.ipaam1.presentation.action.SessionLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.ext.toLiveData
import kotlinx.coroutines.experimental.channels.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IssuesStore @Inject constructor(
        private val dispatcher: Dispatcher
) {
    val sessions: LiveData<List<Issue>> by lazy {
        dispatcher.asChannel<IssueChangedAction>()
                .map { it.issue }
                .toLiveData()
    }

    val loadingState: LiveData<LoadState> by lazy {
        dispatcher.asChannel<IssueLoadStateChangeAction>()
                .map { it.loadState }
                .toLiveData()
    }
}
