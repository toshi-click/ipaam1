package click.toshi.ipaam1.data.db.entity

import android.arch.persistence.room.ColumnInfo

data class LevelEntity(
        @ColumnInfo(name = "level_id")
        var id: Int,
        @ColumnInfo(name = "level_name")
        var name: String
)
