package click.toshi.ipaam1.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "issue")
class IssueEntity(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "issue_name")
        var name: String
)
