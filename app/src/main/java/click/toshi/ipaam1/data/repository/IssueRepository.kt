package click.toshi.ipaam1.data.repository

import android.support.annotation.CheckResult
import click.toshi.ipaam1.model.Issue
import click.toshi.ipaam1.model.Session
import io.reactivex.Flowable
import io.reactivex.Single

interface IssueRepository {
    val issues: Flowable<List<Issue>>

    suspend fun refreshIssues()
//    @CheckResult fun favorite(session: Session.SpeechSession): Single<Boolean>
}
