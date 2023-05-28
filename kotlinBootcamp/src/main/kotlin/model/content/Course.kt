package model.content

class Course(title: String, description: String, private val totalHours: Int) : Content(title, description) {

    override fun xpAwarded(): Double {
        return DEFAULT_XP * totalHours
    }

    override fun toString(): String {
        val descLength: Int = description.length
        val lineBreak = 75
        val fmt = """
     ================================================================================
     | Course: %-69s|
     | Total Hours: %-64s|
     | Awarded XP: %-65.2f|
     ================================================================================
     
     """.trimIndent() + (0 until descLength step lineBreak)
                        .map { i: Int -> description.substring(i, (i + lineBreak + 1).coerceAtMost(descLength)) }
                        .joinToString { str-> wrappedDescriptionLine(str, lineBreak) } +
    "================================================================================\n"
        return fmt.format(title, totalHours, xpAwarded())
    }
}