package click.toshi.ipaam1.data.db.entity

import android.arch.persistence.room.ColumnInfo

data class TopicEntity(
        @ColumnInfo(name = "topic_id")
        var id: Int,
        @ColumnInfo(name = "topic_name")
        var name: String
)
