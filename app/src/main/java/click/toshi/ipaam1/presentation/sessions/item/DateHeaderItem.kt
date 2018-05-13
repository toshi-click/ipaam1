package click.toshi.ipaam1.presentation.sessions.item

import com.xwray.groupie.databinding.BindableItem
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.ItemDateHeaderBinding
import click.toshi.ipaam1.util.ext.toReadableDateString
import click.toshi.ipaam1.util.ext.toReadableTimeString
import java.util.Date

data class DateHeaderItem(
        val startDateTime: Date,
        val endDateTime: Date
) : BindableItem<ItemDateHeaderBinding>(
        startDateTime.hashCode().toLong()
) {
    private val startDateTimePair: ReadableDateTimePair =
            ReadableDateTimePair(
                    startDateTime.toReadableDateString(),
                    startDateTime.toReadableTimeString()
            )
    private val endDateTimePair: ReadableDateTimePair =
            ReadableDateTimePair(
                    endDateTime.toReadableDateString(),
                    endDateTime.toReadableTimeString()
            )

    override fun bind(viewBinding: ItemDateHeaderBinding, position: Int) {
        viewBinding.periodText.text = viewBinding.root.context.getString(
                R.string.time_period,
                startDateTimePair.time,
                endDateTimePair.time
        )
    }

    override fun getLayout(): Int = R.layout.item_date_header
}

data class ReadableDateTimePair(
        val date: String,
        val time: String
) : Comparable<ReadableDateTimePair> {
    override fun compareTo(other: ReadableDateTimePair): Int {
        if (date > other.date) return 1
        if (date < other.date) return -1
        if (time > other.time) return 1
        if (time < other.time) return -1
        return 0
    }
}
