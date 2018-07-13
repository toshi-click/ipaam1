package click.toshi.ipaam1.presentation.issues

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.presentation.action.IssueRefreshLoadStateChangeAction
import click.toshi.ipaam1.presentation.action.SessionRefreshLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.ext.toLiveData
import kotlinx.coroutines.experimental.channels.map
import javax.inject.Inject

class AllIssuesStore @Inject constructor(
        dispatcher: Dispatcher
) : ViewModel() {
    val refreshLoadState: LiveData<LoadState> by lazy {
        dispatcher
                .asChannel<IssueRefreshLoadStateChangeAction>()
                .map { it.loadState }
                .toLiveData()
    }
}
