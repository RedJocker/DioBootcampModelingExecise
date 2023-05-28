package model.content

abstract class Content(val title: String, val description: String) {

    companion object {
        var DEFAULT_XP = 10.00
    }

    abstract fun xpAwarded(): Double


    protected fun wrappedDescriptionLine(descriptionLineWithFirstCharOfNext: String, lineBreak: Int): String {
        val lastIndex = descriptionLineWithFirstCharOfNext.length.coerceAtMost(lineBreak)
        val shouldPutWrapSign = (lastIndex == lineBreak && Character.isLetter(
            descriptionLineWithFirstCharOfNext[lineBreak - 1]
        )
                && Character.isLetter(descriptionLineWithFirstCharOfNext[lineBreak]))
        return String.format("| %-75s%s |\n",
            descriptionLineWithFirstCharOfNext.substring(0, lastIndex).trim { it <= ' ' },
            if (shouldPutWrapSign) "-" else " "
        )
    }
}