package click.toshi.ipaam1.presentation.sessions.item

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import click.toshi.ipaam1.model.Session

class SimpleSessionsSection : Section() {
    fun updateSessions(
            sessions: List<Session>,
            onFavoriteClickListener: (Session.SpeechSession) -> Unit,
            onFeedbackListener: (Session.SpeechSession) -> Unit,
            searchQuery: String = "",
            userIdInDetail: String? = null
    ) {
        val sessionItems = sessions.map {
            when (it) {
                is Session.SpeechSession -> {
                    @Suppress("USELESS_CAST")
                    SpeechSessionItem(
                            session = it,
                            onFavoriteClickListener = onFavoriteClickListener,
                            onFeedbackListener = onFeedbackListener,
                            isShowDayNumber = true,
                            searchQuery = searchQuery,
                            userIdInDetail = userIdInDetail
                    ) as Item<*>
                }
                is Session.SpecialSession -> {
                    @Suppress("USELESS_CAST")
                    SpecialSessionItem(
                            session = it
                    ) as Item<*>
                }
            }
        }
        update(sessionItems)
    }
}
