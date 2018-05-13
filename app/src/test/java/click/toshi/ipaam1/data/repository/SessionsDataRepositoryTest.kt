package click.toshi.ipaam1.data.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import click.toshi.ipaam1.createDummySessionWithSpeakersEntities
import click.toshi.ipaam1.createDummySpeakerEntities
import click.toshi.ipaam1.data.db.FavoriteDatabase
import click.toshi.ipaam1.data.db.SessionDatabase
import click.toshi.ipaam1.data.db.entity.mapper.toSession
import click.toshi.ipaam1.data.db.fixeddata.SpecialSessions
import click.toshi.ipaam1.util.rx.TestSchedulerProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.threeten.bp.LocalDate

@RunWith(RobolectricTestRunner::class)
class SessionsDataRepositoryTest {
    private val sessionDatabase: SessionDatabase = mock()
    private val favoriteDatabase: FavoriteDatabase = mock()

    @Before fun init() {
        whenever(sessionDatabase.getAllRoom()).doReturn(Flowable.just(mock()))
        whenever(sessionDatabase.getAllTopic()).doReturn(Flowable.just(mock()))
        whenever(sessionDatabase.getAllSessions()).doReturn(Flowable.just(mock()))
        whenever(sessionDatabase.getAllSpeaker()).doReturn(Flowable.just(mock()))
        whenever(favoriteDatabase.favorites).doReturn(Flowable.just(emptyList()))
    }

    @Test fun sessions() {
        val sessions = createDummySessionWithSpeakersEntities()
        val speakers = createDummySpeakerEntities()

        whenever(sessionDatabase.getAllSessions()).doReturn(Flowable.just(sessions))
        whenever(sessionDatabase.getAllSpeaker()).doReturn(Flowable.just(speakers))
        val sessionDataRepository = SessionDataRepository(mock(),
                sessionDatabase,
                favoriteDatabase,
                TestSchedulerProvider())

        sessionDataRepository
                .sessions
                .test()
                .assertValueAt(0,
                        sessions.map {
                            it.toSession(speakers,
                                    listOf(),
                                    LocalDate.of(1, 1, 1))
                        }
                                + SpecialSessions.getSessions()
                )

        verify(sessionDatabase).getAllSessions()
    }
}
