package model.content

import model.Dev
import java.time.LocalDate
import java.util.function.Consumer


class Bootcamp : Content {
    private val initialDate: LocalDate
    private val finalDate: LocalDate
    private val devs: MutableSet<Dev>
    private val contents: LinkedHashSet<Content>

    constructor(title: String, description: String) : super(title, description) {
        initialDate = LocalDate.now()
        finalDate = initialDate.plusDays(45)
        devs = HashSet()
        contents = LinkedHashSet()
    }

    constructor(title: String, description: String, devs: MutableSet<Dev>, contents: LinkedHashSet<Content>)
            : super(title, description) {
        initialDate = LocalDate.now()
        finalDate = initialDate.plusDays(45)
        this.devs = devs
        this.contents = contents
        this.devs.forEach { dev: Dev ->
            this.contents.forEach  { content ->
                dev.subscribeContent(
                    content
                )
            }
        }
    }

    fun subscribe(dev: Dev) {
        devs.add(dev)
        contents.forEach(Consumer { content ->
            dev.subscribeContent(
                content
            )
        })
        dev.subscribeContent(this)
    }

    fun unsubscribe(dev: Dev) {
        devs.remove(dev)
        contents.forEach(Consumer { content ->
            dev.unsubscribeContent(
                content
            )
        })
    }

    fun subscribedDevs(): String {
        return devs.joinToString("\n") { obj: Dev -> obj.toString() }
    }

    override fun xpAwarded(): Double {
        return contents.size * 10 * DEFAULT_XP
    }

    override fun toString(): String {
        return """
            
-------------++++++++++++++  B  O  O  T  C  A  M  P  ++++++++++++++-------------
:Title: $title
:Description: $description
:Initial Date: $initialDate
:Final Date: $finalDate
:XP Awarded: ${xpAwarded()}
**** CONTENT ****
${
contents.joinToString("\n") { obj: Content -> obj.toString() }
}-------------++++++++++++++  B  O  O  T  C  A  M  P  ++++++++++++++-------------

            """
    }
}