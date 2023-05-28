import model.Dev
import model.content.Bootcamp
import model.content.Course
import model.content.Mentorship
import java.time.LocalDateTime

fun main() {
    val course = Course("JavaOO", "Object Orient java", 20)


    val typesVsClasses = Mentorship(
        "Types vs Classes",
        "A domain modeling comparative approach between Haskell and Java",
        LocalDateTime.now()
    )

    val contents = LinkedHashSet(listOf(course, typesVsClasses))

    val dev = Dev("Mr. Yes")
    println(dev)

    val bootcamp = Bootcamp(
        "Bootcamp Domain Driven Java",
        "Dominating the complexity of your domain",
        HashSet(),
        contents
    )

    println(bootcamp)

    bootcamp.subscribe(dev)
    dev.nextContent()?.also(dev::completeContent) ?: run {
        println("Congratulations, you finished all subscribed content\n.Subscribe to more to continue learning.")
    }

    println(bootcamp.subscribedDevs())
}