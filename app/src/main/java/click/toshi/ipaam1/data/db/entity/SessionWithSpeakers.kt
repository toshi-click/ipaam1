package click.toshi.ipaam1.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class SessionWithSpeakers(
        @Embedded var session: SessionEntity? = null,
        @Relation(
                parentColumn = "id",
                entityColumn = "sessionId",
                projection = ["speakerId"],
                entity = SessionSpeakerJoinEntity::class)
        var speakerIdList: List<String> = emptyList())
