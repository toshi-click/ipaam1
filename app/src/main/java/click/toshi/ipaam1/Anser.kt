package click.toshi.ipaam1
import java.util.Date

sealed class Anser(
        open val id: String,
        open val questionId: String,
        open val answerNumber: Int,
        open val updateAt: Date
)
