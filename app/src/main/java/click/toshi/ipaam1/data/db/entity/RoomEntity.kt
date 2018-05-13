package click.toshi.ipaam1.data.db.entity

import android.arch.persistence.room.ColumnInfo

data class RoomEntity(
        @ColumnInfo(name = "room_id")
        var id: Int,
        @ColumnInfo(name = "room_name")
        var name: String
)
