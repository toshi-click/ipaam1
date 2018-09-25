package click.toshi.ipaam1.data.entity

import java.util.*

data class Question(
        val id: String,
        val year: Int,
        val month: Int,
        val statement: String,
        val answerNumber: Int,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String,
        val answerRate: Double,
        val imageUrl: String,
        val updateAt: Date,
        val dayNumber: Int,
        val startTime: Date,
        val endTime: Date
)
