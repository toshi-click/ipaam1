package click.toshi.ipaam1.presentation.sessions

import click.toshi.ipaam1.data.repository.SessionRepository
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.presentation.action.SessionRefreshLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.defaultErrorHandler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class AllSessionsActionCreator @Inject constructor(
        private val dispatcher: Dispatcher,
        private val repository: SessionRepository
) {

    fun refreshSessions() {
        launch(UI) {
            try {
                dispatcher.send(SessionRefreshLoadStateChangeAction(LoadState.Loading))
                repository.refreshSessions()
                dispatcher.send(SessionRefreshLoadStateChangeAction(LoadState.Finished))
            } catch (e: Exception) {
                dispatcher.send(
                        SessionRefreshLoadStateChangeAction(LoadState.Error(e))
                )
            }
        }
    }

    fun favorite(session: Session.SpeechSession) {
        val favoriteSingle: Single<Boolean> = repository.favorite(session)
        favoriteSingle
                .subscribeBy(onError = defaultErrorHandler())
    }
}
