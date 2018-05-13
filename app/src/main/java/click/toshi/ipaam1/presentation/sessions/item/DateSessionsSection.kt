package click.toshi.ipaam1.presentation.sessions.item

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import click.toshi.ipaam1.model.Session
import java.util.Date
import java.util.SortedMap

class DateSessionsSection : Section() {
    fun updateSessions(
            sessions: List<Session>,
            onFavoriteClickListener: (Session.SpeechSession) -> Unit,
            onFeedbackListener: (Session.SpeechSession) -> Unit,
            isShowDayNumber: Boolean = false
    ) {
        val sessionItems = sessions.map {
            when (it) {
                is Session.SpeechSession -> {
                    @Suppress("USELESS_CAST")
                    SpeechSessionItem(
                            session = it,
                            onFavoriteClickListener = onFavoriteClickListener,
                            onFeedbackListener = onFeedbackListener,
                            isShowDayNumber = isShowDayNumber
                    ) as SessionItem
                }
                is Session.SpecialSession -> {
                    @Suppress("USELESS_CAST")
                    SpecialSessionItem(
                            session = it,
                            isShowDayNumber = isShowDayNumber
                    ) as SessionItem
                }
            }
        }

        val dateSpeechSessionItemsMap: SortedMap<Date, List<SessionItem>> =
                sessionItems.groupBy { it.session.startTime }.toSortedMap()

        val dateSessions = arrayListOf<Item<*>>()
        dateSpeechSessionItemsMap.keys.forEach { startTime ->
            startTime ?: return@forEach
            val list = dateSpeechSessionItemsMap[startTime]

            val endTime = list!![0].session.endTime
            dateSessions.add(DateHeaderItem(startTime, endTime))
            @Suppress("UNCHECKED_CAST")
            dateSessions.addAll(list.toMutableList() as List<Item<*>>)
        }
        update(dateSessions)
    }

    fun getDateNumberOrNull(position: Int): Int? {
        if (position < 0) return null

        var item = getItemOrNull(position) ?: return null
        item = item as? SpeechSessionItem ?: getItemOrNull(position + 1) ?: return null
        return when (item) {
            is SpeechSessionItem -> {
                item.session.dayNumber
            }
            is SpecialSessionItem -> {
                item.session.dayNumber
            }
            else -> null
        }
    }

    fun getDateHeaderPositionByDate(date: Date): Int {
        var position = 0
        if (itemCount == 0) return position

        val items = 0.until(itemCount).map { getItem(it) }
        if (items.isEmpty()) return position

        items.forEachIndexed { index, item ->
            if (item !is DateHeaderItem) return@forEachIndexed

            position = index

            if (date.getTime() <= item.endDateTime.getTime()) return position
        }

        return position
    }

    private fun getItemOrNull(i: Int): Item<*>? {
        if (itemCount <= i) {
            return null
        }
        return getItem(i)
    }
}
