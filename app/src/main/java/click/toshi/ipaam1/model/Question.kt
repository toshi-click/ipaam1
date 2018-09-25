//package click.toshi.ipaam1.model
//
//import java.util.Date
//
//sealed class Question(
//        open val id: String,
//        open val year: Int,
//        open val month: Int,
//        open val statement: String,
//        open val answerNumber: Int,
//        open val answer1: String,
//        open val answer2: String,
//        open val answer3: String,
//        open val answer4: String,
//        open val answerRate: Double,
//        open val imageUrl: String,
//        open val updateAt: Date,
//        open val dayNumber: Int,
//        open val startTime: Date,
//        open val endTime: Date
//) {
//    data class SpeechQuestion(
//            override val id: String,
//            override val year: Int,
//            override val month: Int,
//            override val statement: String,
//            override val answerNumber: Int,
//            override val answer1: String,
//            override val answer2: String,
//            override val answer3: String,
//            override val answer4: String,
//            override val answerRate: Double,
//            override val imageUrl: String,
//            override val updateAt: Date,
//            override val dayNumber: Int,
//            override val startTime: Date,
//            override val endTime: Date,
//            val title: String,
//            val desc: String,
//            val room: Room,
//            val format: String,
//            val language: String,
//            val topic: Topic,
//            val level: Level,
//            val isFavorited: Boolean,
//            val speakers: List<Speaker>,
//            val message: SessionMessage?
//    ) : Question(id,year,month,statement,answerNumber,answer1,answer2,answer3,answer4,answerRate,imageUrl,updateAt, dayNumber, startTime, endTime)
//
//    data class SpecialQuestion(
//            override val id: String,
//            override val year: Int,
//            override val month: Int,
//            override val statement: String,
//            override val answerNumber: Int,
//            override val answer1: String,
//            override val answer2: String,
//            override val answer3: String,
//            override val answer4: String,
//            override val answerRate: Double,
//            override val imageUrl: String,
//            override val updateAt: Date,
//            override val dayNumber: Int,
//            override val startTime: Date,
//            override val endTime: Date,
//            val title: Int,
//            val room: Room?
//    ) : Question(id,year,month,statement,answerNumber,answer1,answer2,answer3,answer4,answerRate,imageUrl,updateAt, dayNumber, startTime, endTime)
//
//    val isFinished: Boolean
//        get() = System.currentTimeMillis() > endTime.time
//
//    val isOnGoing: Boolean
//        get() = System.currentTimeMillis() in startTime.time..endTime.time
//}
