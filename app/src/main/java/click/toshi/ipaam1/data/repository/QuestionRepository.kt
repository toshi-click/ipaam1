package click.toshi.ipaam1.data.repository

import android.support.annotation.CheckResult
import click.toshi.ipaam1.model.Question
import click.toshi.ipaam1.model.Session
import io.reactivex.Flowable
import io.reactivex.Single

interface QuestionRepository {
    val sessions: Flowable<List<Question>>

    suspend fun refreshQuestions()
    @CheckResult fun favorite(question: Question.SpeechQuestion): Single<Boolean>
}
