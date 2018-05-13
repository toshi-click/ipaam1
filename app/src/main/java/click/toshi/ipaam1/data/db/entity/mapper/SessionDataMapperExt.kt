package click.toshi.ipaam1.data.db.entity.mapper

import android.support.annotation.VisibleForTesting
import click.toshi.ipaam1.data.db.entity.RoomEntity
import click.toshi.ipaam1.data.db.entity.SessionWithSpeakers
import click.toshi.ipaam1.data.db.entity.SpeakerEntity
import click.toshi.ipaam1.data.db.entity.TopicEntity
import click.toshi.ipaam1.model.Level
import click.toshi.ipaam1.model.Room
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.model.SessionMessage
import click.toshi.ipaam1.model.Speaker
import click.toshi.ipaam1.model.Topic
import click.toshi.ipaam1.util.ext.atJST
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import java.util.Date

fun SessionWithSpeakers.toSession(
        speakerEntities: List<SpeakerEntity>,
        favList: List<Int>?,
        firstDay: LocalDate
): Session.SpeechSession {
    val sessionEntity = session!!
    require(speakerIdList.isNotEmpty())
    val speakers = speakerIdList.map { speakerId ->
        val speakerEntity = speakerEntities.first { it.id == speakerId }
        speakerEntity.toSpeaker()
    }
    require(speakers.isNotEmpty())
    return Session.SpeechSession(
            id = sessionEntity.id,
            // dayNumber is starts with 1. Example: First day = 1, Second day = 2. So I plus 1 to period days
            dayNumber = Period.between(
                    firstDay, sessionEntity.stime.atJST().toLocalDate()).days + 1,
            startTime = Date(sessionEntity.stime.toEpochMilli()),
            endTime = Date(sessionEntity.etime.toEpochMilli()),
            title = sessionEntity.title,
            desc = sessionEntity.desc,
            room = Room(sessionEntity.room.id, sessionEntity.room.name),
            format = sessionEntity.sessionFormat,
            language = sessionEntity.language,
            topic = Topic(sessionEntity.topic.id, sessionEntity.topic.name),
            level = Level.of(sessionEntity.level.id, sessionEntity.level.name),
            isFavorited = favList!!.map { it.toString() }.contains(sessionEntity.id),
            speakers = speakers,
            message = if (sessionEntity.message == null) {
                null
            } else {
                SessionMessage(sessionEntity.message.ja, sessionEntity.message.en)
            }
    )
}

fun SpeakerEntity.toSpeaker(): Speaker = Speaker(
        id = id,
        name = name,
        tagLine = tagLine,
        imageUrl = imageUrl,
        twitterUrl = twitterUrl,
        companyUrl = companyUrl,
        blogUrl = blogUrl,
        githubUrl = githubUrl
)

@VisibleForTesting
fun List<RoomEntity>.toRooms() =
        map { Room(it.id, it.name) }

@VisibleForTesting
fun List<TopicEntity>.toTopics() =
        map { Topic(it.id, it.name) }
