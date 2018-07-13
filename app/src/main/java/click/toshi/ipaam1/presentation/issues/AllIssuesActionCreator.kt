package click.toshi.ipaam1.presentation.issues

import click.toshi.ipaam1.data.repository.SessionRepository
import click.toshi.ipaam1.model.Issue
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.presentation.action.IssueRefreshLoadStateChangeAction
import click.toshi.ipaam1.presentation.action.SessionRefreshLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.defaultErrorHandler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class AllIssuesActionCreator @Inject constructor(
        private val dispatcher: Dispatcher,
        private val repository: IssueRepository
) {

    fun refreshSessions() {
        launch(UI) {
            try {
                dispatcher.send(IssueRefreshLoadStateChangeAction(LoadState.Loading))
                repository.refreshIssues()
                dispatcher.send(IssueRefreshLoadStateChangeAction(LoadState.Finished))
            } catch (e: Exception) {
                dispatcher.send(
                        IssueRefreshLoadStateChangeAction(LoadState.Error(e))
                )
            }
        }
    }

//    fun favorite(issue: Issue.SpeechSession) {
//        val favoriteSingle: Single<Boolean> = repository.favorite(session)
//        favoriteSingle
//                .subscribeBy(onError = defaultErrorHandler())
//    }
}
