package click.toshi.ipaam1.util.ext

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

fun Instant.atJST(): ZonedDateTime {
    return atZone(ZoneId.of("JST", ZoneId.SHORT_IDS))
}
