package click.toshi.ipaam1.data.db

import android.arch.persistence.room.RoomDatabase
import android.support.annotation.CheckResult
import click.toshi.ipaam1.data.api.response.Response
import click.toshi.ipaam1.data.api.response.mapper.toSessionEntities
import click.toshi.ipaam1.data.api.response.mapper.toSessionSpeakerJoinEntities
import click.toshi.ipaam1.data.api.response.mapper.toSpeakerEntities
import click.toshi.ipaam1.data.db.dao.SessionDao
import click.toshi.ipaam1.data.db.dao.SessionSpeakerJoinDao
import click.toshi.ipaam1.data.db.dao.SpeakerDao
import click.toshi.ipaam1.data.db.entity.RoomEntity
import click.toshi.ipaam1.data.db.entity.SessionWithSpeakers
import click.toshi.ipaam1.data.db.entity.SpeakerEntity
import click.toshi.ipaam1.data.db.entity.TopicEntity
import io.reactivex.Flowable
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class SessionRoomDatabase @Inject constructor(
        private val database: RoomDatabase,
        private val sessionDao: SessionDao,
        private val speakerDao: SpeakerDao,
        private val sessionSpeakerJoinDao: SessionSpeakerJoinDao
) : SessionDatabase {
    @CheckResult override fun getAllSessions(): Flowable<List<SessionWithSpeakers>> =
            sessionSpeakerJoinDao.getAllSessions()

    @CheckResult
    override fun getAllSpeaker(): Flowable<List<SpeakerEntity>> = speakerDao.getAllSpeaker()

    @CheckResult override fun getAllRoom(): Flowable<List<RoomEntity>> = sessionDao.getAllRoom()

    @CheckResult override fun getAllTopic(): Flowable<List<TopicEntity>> = sessionDao.getAllTopic()

    override suspend fun save(response: Response) = withContext(CommonPool) {
        database.runInTransaction {
            speakerDao.clearAndInsert(response.speakers.toSpeakerEntities())
            val sessions = response.sessions
            val sessionEntities = sessions.toSessionEntities(response.categories, response.rooms)
            sessionDao.clearAndInsert(sessionEntities)
            sessionSpeakerJoinDao.insert(sessions.toSessionSpeakerJoinEntities())
        }
    }
}
