package click.toshi.ipaam1.data.db

import android.support.annotation.CheckResult
import click.toshi.ipaam1.data.api.response.Response
import click.toshi.ipaam1.data.db.entity.RoomEntity
import click.toshi.ipaam1.data.db.entity.SessionWithSpeakers
import click.toshi.ipaam1.data.db.entity.SpeakerEntity
import click.toshi.ipaam1.data.db.entity.TopicEntity
import io.reactivex.Flowable

interface SessionDatabase {
    @CheckResult fun getAllSessions(): Flowable<List<SessionWithSpeakers>>
    @CheckResult fun getAllSpeaker(): Flowable<List<SpeakerEntity>>
    @CheckResult fun getAllRoom(): Flowable<List<RoomEntity>>
    @CheckResult fun getAllTopic(): Flowable<List<TopicEntity>>
    suspend fun save(response: Response)
}
