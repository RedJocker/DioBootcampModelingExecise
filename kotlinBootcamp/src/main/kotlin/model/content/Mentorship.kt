package model.content

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Mentorship(title: String, description: String, private val date: LocalDateTime) : Content(title, description) {

    override fun xpAwarded(): Double {
        return DEFAULT_XP + 20.0
    }

    override fun toString(): String {
        val descLength: Int = description.length
        val lineBreak = 75
        val fmt = """
     ================================================================================
     | Mentorship: %-65s|
     | Date: %-71s|
     | Awarded XP: %-65.2f|
     ================================================================================
     
     """.trimIndent() + (0 until descLength step lineBreak)
            .map { i: Int -> description.substring(i, (i + lineBreak + 1).coerceAtMost(descLength)) }
            .joinToString { str-> wrappedDescriptionLine(str, lineBreak) } +
                "================================================================================\n"
        return String.format(fmt, title, date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), xpAwarded())
    }
}