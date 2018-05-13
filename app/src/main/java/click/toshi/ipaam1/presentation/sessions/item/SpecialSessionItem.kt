package click.toshi.ipaam1.presentation.sessions.item

import com.xwray.groupie.databinding.BindableItem
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.ItemSpecialSessionBinding
import click.toshi.ipaam1.model.Session

data class SpecialSessionItem(
        override val session: Session.SpecialSession,
        private val isShowDayNumber: Boolean = false
) : BindableItem<ItemSpecialSessionBinding>(
        session.id.toLong()
), SessionItem {

    override fun bind(viewBinding: ItemSpecialSessionBinding, position: Int) {
        viewBinding.session = session
        viewBinding.isShowDayNumber = isShowDayNumber
    }

    override fun getLayout(): Int = R.layout.item_special_session
}
