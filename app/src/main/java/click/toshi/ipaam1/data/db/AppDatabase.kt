package click.toshi.ipaam1.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import click.toshi.ipaam1.data.db.dao.IssueDao
import click.toshi.ipaam1.data.db.dao.SessionDao
import click.toshi.ipaam1.data.db.dao.SessionSpeakerJoinDao
import click.toshi.ipaam1.data.db.dao.SpeakerDao
import click.toshi.ipaam1.data.db.entity.IssueEntity
import click.toshi.ipaam1.data.db.entity.SessionEntity
import click.toshi.ipaam1.data.db.entity.SessionSpeakerJoinEntity
import click.toshi.ipaam1.data.db.entity.SpeakerEntity
import click.toshi.ipaam1.data.db.entity.mapper.Converters

@Database(
        entities = [
            (SessionEntity::class),
            (SpeakerEntity::class),
            (SessionSpeakerJoinEntity::class),
            (IssueEntity::class)
        ],
        version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun speakerDao(): SpeakerDao
    abstract fun sessionSpeakerDao(): SessionSpeakerJoinDao
    abstract fun issueDao(): IssueDao
}
