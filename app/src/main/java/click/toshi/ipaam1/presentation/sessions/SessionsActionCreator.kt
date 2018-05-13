package click.toshi.ipaam1.presentation.sessions

import click.toshi.ipaam1.data.repository.SessionRepository
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.presentation.action.SessionChangedAction
import click.toshi.ipaam1.presentation.action.SessionLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.defaultErrorHandler
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionsActionCreator @Inject constructor(
        private val dispatcher: Dispatcher,
        private val repository: SessionRepository
) {

    fun subscribeSessionChange() {
        repository.sessions
                .doOnSubscribe {
                    dispatcher.send(SessionLoadStateChangeAction(LoadState.Loading))
                }
                .subscribeBy(
                        onNext = {
                            dispatcher.send(SessionLoadStateChangeAction(LoadState.Finished))
                            dispatcher.send(SessionChangedAction(it))
                        },
                        onError = defaultErrorHandler()
                )
    }

}
