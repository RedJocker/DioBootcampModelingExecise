package model

import model.content.Content
import java.util.*


class Dev {
    private val name: String
    private val subscribedContent: LinkedHashSet<Content>
    private val completedContent: LinkedHashSet<Content>

    constructor(name: String) {
        this.name = name
        subscribedContent = LinkedHashSet()
        completedContent = LinkedHashSet()
    }

    constructor(updateName: String, contentToCopy: Dev) {
        name = updateName
        subscribedContent = contentToCopy.subscribedContent
        completedContent = contentToCopy.completedContent
    }

    val xp: Double get() = completedContent.sumOf { obj: Content -> obj.xpAwarded() }

    fun subscribeContent(content: Content) {
        if (!completedContent.contains(content)) {
            subscribedContent.add(content)
        }
    }

    fun unsubscribeContent(content: Content) {
        subscribedContent.remove(content)
    }

    fun nextContent(): Content? {
        return subscribedContent.firstOrNull()
    }

    fun completeContent(content: Content): Boolean {
        return if (subscribedContent.contains(content)) {
            subscribedContent.remove(content)
            completedContent.add(content)
            true
        } else {
            false
        }
    }

    override fun toString(): String {
        return "(Dev :Name $name :Xp $xp)"
    }
}