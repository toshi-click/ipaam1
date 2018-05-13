package click.toshi.ipaam1.data.db.entity.mapper

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant

object Converters {
    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? =
            if (value == null) {
                null
            } else {
                Instant.ofEpochSecond(value)
            }

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? =
            date?.epochSecond
}
