package click.toshi.ipaam1.presentation.sessions.item

import com.xwray.groupie.databinding.BindableItem
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.ItemSpeechSessionBinding
import click.toshi.ipaam1.model.Lang
import click.toshi.ipaam1.model.Level
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.util.ext.context
import click.toshi.ipaam1.util.ext.drawable
import click.toshi.ipaam1.util.lang

data class SpeechSessionItem(
        override val session: Session.SpeechSession,
        private val onFavoriteClickListener: (Session.SpeechSession) -> Unit,
        private val onFeedbackListener: (Session.SpeechSession) -> Unit,
        private val isShowDayNumber: Boolean = false,
        private val searchQuery: String = "",
        private val simplify: Boolean = false,
        private val userIdInDetail: String? = null
) : BindableItem<ItemSpeechSessionBinding>(
        session.id.toLong()
), SessionItem {

    override fun bind(viewBinding: ItemSpeechSessionBinding, position: Int) {
        viewBinding.session = session
        viewBinding.searchQuery = searchQuery
        viewBinding.topic.text = session.topic.name
        viewBinding.favorite.setOnClickListener {
            onFavoriteClickListener(session)
        }
        viewBinding.speakerSummary.setSpeakerIdInDetail(userIdInDetail)
        viewBinding.isShowDayNumber = isShowDayNumber
        viewBinding.simplify = simplify
        viewBinding.goToFeedback.setOnClickListener {
            onFeedbackListener(session)
        }
        session.message?.let { message ->
            viewBinding.message.text = if (lang() == Lang.JA) {
                message.jaMessage
            } else {
                message.enMessage
            }
        }

        val levelDrawable = viewBinding.context.drawable(when (session.level) {
            is Level.Beginner -> R.drawable.ic_beginner_lightgreen_20dp
            is Level.IntermediateOrExpert -> R.drawable.ic_intermediate_senior_bluegray_20dp
            is Level.Niche -> R.drawable.ic_niche_cyan_20dp
        })
        viewBinding.level.setImageDrawable(levelDrawable)
    }

    override fun getLayout(): Int = R.layout.item_speech_session
}
