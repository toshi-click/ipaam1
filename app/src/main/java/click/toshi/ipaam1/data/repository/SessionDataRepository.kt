package click.toshi.ipaam1.data.repository

import android.support.annotation.CheckResult
import android.support.annotation.VisibleForTesting
import click.toshi.ipaam1.data.api.ApplicationApi
import click.toshi.ipaam1.data.db.FavoriteDatabase
import click.toshi.ipaam1.data.db.SessionDatabase
import click.toshi.ipaam1.data.db.entity.mapper.toSession
import click.toshi.ipaam1.data.db.fixeddata.SpecialSessions
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.util.ext.atJST
import click.toshi.ipaam1.util.rx.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.Flowables
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import timber.log.Timber
import javax.inject.Inject

class SessionDataRepository @Inject constructor(
        private val api: ApplicationApi,
        private val sessionDatabase: SessionDatabase,
        private val favoriteDatabase: FavoriteDatabase,
        private val schedulerProvider: SchedulerProvider
) : SessionRepository {

    override val sessions: Flowable<List<Session>> =
            Flowables.combineLatest(
                    sessionDatabase.getAllSessions()
                            .filter { it.isNotEmpty() }
                            .doOnNext { if (DEBUG) Timber.d("getAllSessions") },
                    sessionDatabase.getAllSpeaker()
                            .filter { it.isNotEmpty() }
                            .doOnNext { if (DEBUG) Timber.d("getAllSpeaker") },
                    Flowable.concat(
                            Flowable.just(listOf()),
                            favoriteDatabase.favorites.onErrorReturn { listOf() }
                    )
                            .doOnNext { if (DEBUG) Timber.d("favorites") },
                    { sessionEntities, speakerEntities, favList ->
                        val firstDay = sessionEntities.first().session!!.stime.atJST().toLocalDate()
                        val speakerSessions = sessionEntities
                                .map { it.toSession(speakerEntities, favList, firstDay) }
                                .sortedWith(compareBy(
                                        { it.startTime.getTime() },
                                        { it.room.id }
                                ))

                        speakerSessions + specialSessions
                    })
                    .subscribeOn(schedulerProvider.io())
                    .doOnNext {
                        if (DEBUG) Timber.d("size:${it.size} current:${System.currentTimeMillis()}")
                    }

    @VisibleForTesting
    val specialSessions: List<Session.SpecialSession> by lazy {
        SpecialSessions.getSessions()
    }

    @CheckResult override fun favorite(session: Session.SpeechSession): Single<Boolean> =
            favoriteDatabase.favorite(session)

    @CheckResult override suspend fun refreshSessions() = withContext(CommonPool) {
        val response = api.getSessions().await()
        sessionDatabase.save(response)
    }

    companion object {
        const val DEBUG = false
    }
}
