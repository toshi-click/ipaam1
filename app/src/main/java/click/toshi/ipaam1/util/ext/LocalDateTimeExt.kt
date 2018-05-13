package click.toshi.ipaam1.util.ext

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

fun LocalDateTime.atJST(): ZonedDateTime {
    return atZone(ZoneId.of("JST", ZoneId.SHORT_IDS))
}
