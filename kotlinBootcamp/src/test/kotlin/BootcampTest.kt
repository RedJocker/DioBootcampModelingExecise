import model.Dev
import model.content.Bootcamp
import model.content.Content
import model.content.Course
import model.content.Mentorship
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class BootcampTest {
    @Test
    fun testContent() {
        val testTitle = "JavaOO"
        val testDescription = "exercise on object orientation with java"
        val content: Content = object : Content(testTitle, testDescription) {
            override fun xpAwarded(): Double {
                return DEFAULT_XP
            }
        }
        assertEquals(testTitle, content.title,
            "Title should be the same as the one passed on constructor"
        )
        assertEquals(testDescription, content.description,
            "Description should be the same as the one passed on constructor"
        )
    }

    @Test
    fun testCourse() {
        val testTitle = "JavaOO"
        val testDescription = "exercise on object orientation with java"
        val testTotalHours = 40
        val content: Content = Course(testTitle, testDescription, testTotalHours)
        assertEquals(testTitle, content.title,
            "Title should be the same as the one passed on constructor"
        )
        assertEquals(testDescription, content.description,
            "Description should be the same as the one passed on constructor"
        )
        assertEquals(testTotalHours * Content.DEFAULT_XP, content.xpAwarded(),
            "xpAwarded expected to be the product of 'total hours' with 'default xp'"
        )
    }


    @Test
    fun testMentorship() {
        val testTitle = "JavaOO"
        val testDescription = "exercise on object orientation with java"
        val content: Content = Mentorship(testTitle, testDescription, LocalDateTime.now())
        assertEquals(testTitle, content.title,
            "Title should be the same as the one passed on constructor"
        )
        assertEquals(testDescription, content.description,
            "Description should be the same as the one passed on constructor"
        )
        assertEquals(20 + Content.DEFAULT_XP, content.xpAwarded(),
            "xpAwarded expected to be the sum of 'default xp' with 20"
        )
    }

    @Test
    fun testDev() {
        val course = Course("JavaOO", "Object Orient java", 20)
        val mentorship = Mentorship(
            "Types vs Classes",
            "A domain modeling comparative approach between Haskell and Java",
            LocalDateTime.now()
        )
        val dev = Dev("Mr. Yes")

        val devInitialXp: Double = dev.xp
        assertEquals(0.0, devInitialXp, "dev initial xp should be 0.0")

        dev.subscribeContent(course)
        dev.subscribeContent(mentorship)

        val actualFirstContent = dev.nextContent()
        val messageNextContent = "nextContent should retrieve subscriptions in order of subscription"
        assertEquals(course, actualFirstContent, messageNextContent)

        dev.completeContent(actualFirstContent!!)
        val devXpAfterCourse: Double = dev.xp
        val expectedXpAfterCourse = course.xpAwarded()
        assertEquals(
            expectedXpAfterCourse, devXpAfterCourse,
            "after just course complete dev xp should match the completed course"
        )

        val actualSecondContent = dev.nextContent()
        val messageSecondContent =
            "After completing the first content nextContent should retrieve the second subscribed content"
        assertEquals(mentorship, actualSecondContent, messageSecondContent)

        dev.completeContent(actualSecondContent!!)

        val devXpAfterCourseAndMentorship: Double = dev.xp
        val expectedXpAfterCourseAndMentorship = course.xpAwarded() + mentorship.xpAwarded()
        assertEquals(
            expectedXpAfterCourseAndMentorship, devXpAfterCourseAndMentorship,
            "after just course and mentorship complete dev xp should match the sum of both course and mentorship xp"
        )

        dev.subscribeContent(course)
        val shouldBeNull = dev.nextContent()
        val messageShouldBeNull =
            "When the list of subscriptions is empty and an already complete content is subscribed then this content" +
                    "should not be added to the subscription and nextContent should return empty"
        assertNull(shouldBeNull, messageShouldBeNull)
    }

    @Test
    fun testBootcamp() {
        val course = Course("JavaOO", "Object Orient java", 20)
        val mentorship = Mentorship(
            "Types vs Classes",
            "A domain modeling comparative approach between Haskell and Java",
            LocalDateTime.now()
        )
        val contents = LinkedHashSet(listOf(course, mentorship))

        val dev = Dev("Mr. Yes")
        val bootcamp = Bootcamp(
            "Bootcamp Domain Driven Java",
            "Dominating the complexity of your domain",
            HashSet(),
            contents
        )

        val expectedSubscribedEmpty = ""
        val actualSubscribedEmpty = bootcamp.subscribedDevs()
        assertEquals(expectedSubscribedEmpty, actualSubscribedEmpty)

        bootcamp.subscribe(dev)
        val expectedSubscribedDevs = dev.toString()
        val actualSubscribedDevs = bootcamp.subscribedDevs()
        assertEquals(expectedSubscribedDevs, actualSubscribedDevs)

        val actualXpAwarded = bootcamp.xpAwarded()
        val expectedXpAwarded = contents.size * 10 * Content.DEFAULT_XP
        assertEquals(expectedXpAwarded, actualXpAwarded)

        dev.nextContent()?.also { content -> dev.completeContent(content) }

        val expectedDevXpAfterCourse = course.xpAwarded()
        val actualDevXpAfterCourse: Double = dev.xp
        assertEquals(expectedDevXpAfterCourse, actualDevXpAfterCourse)

        dev.nextContent()?.also { content -> dev.completeContent(content) }
        val expectedDevXpAfterMentorship = course.xpAwarded() + mentorship.xpAwarded()
        val actualDevXpAfterMentorship: Double = dev.xp
        assertEquals(expectedDevXpAfterMentorship, actualDevXpAfterMentorship)

        dev.nextContent()?.also { content -> dev.completeContent(content) }
        val expectedDevXpAfterBootcampComplete = course.xpAwarded() + mentorship.xpAwarded() + bootcamp.xpAwarded()
        val actualDevXpAfterBootcampComplete: Double = dev.xp
        assertEquals(expectedDevXpAfterBootcampComplete, actualDevXpAfterBootcampComplete)

        val contentEmpty = dev.nextContent()
        assertNull(contentEmpty)
    }
}