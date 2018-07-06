package click.toshi.ipaam1.presentation.question

import click.toshi.ipaam1.data.repository.QuestionRepository
import click.toshi.ipaam1.data.repository.SessionRepository
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.presentation.action.QuestionChangedAction
import click.toshi.ipaam1.presentation.action.QuestionLoadStateChangeAction
import click.toshi.ipaam1.presentation.action.SessionChangedAction
import click.toshi.ipaam1.presentation.action.SessionLoadStateChangeAction
import click.toshi.ipaam1.presentation.dispacher.Dispatcher
import click.toshi.ipaam1.util.defaultErrorHandler
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionActionCreator @Inject constructor(
        private val dispatcher: Dispatcher,
        private val repository: QuestionRepository
) {

    fun subscribeQuestionChange() {
        repository.sessions
                .doOnSubscribe {
                    dispatcher.send(QuestionLoadStateChangeAction(LoadState.Loading))
                }
                .subscribeBy(
                        onNext = {
                            dispatcher.send(QuestionLoadStateChangeAction(LoadState.Finished))
                            dispatcher.send(QuestionChangedAction(it))
                        },
                        onError = defaultErrorHandler()
                )
    }

}
