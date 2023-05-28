import model.Dev;
import model.content.Bootcamp;
import model.content.Content;
import model.content.Course;
import model.content.Mentorship;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static model.content.Content.DEFAULT_XP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BootcampTest {

    @Test
    void testContent() {
        final String testTitle = "JavaOO";
        final String testDescription = "exercise on object orientation with java";
        final Content content = new Content(testTitle, testDescription) {
            @Override
            public double xpAwarded() {
                return DEFAULT_XP;
            }
        };

        assertEquals(
            testTitle,
            content.getTitle(),
            "Title should be the same as the one passed on constructor"
        );

        assertEquals(
                testDescription,
                content.getDescription(),
                "Description should be the same as the one passed on constructor"
        );
    }

    @Test
    void testCourse() {
        final String testTitle = "JavaOO";
        final String testDescription = "exercise on object orientation with java";
        final int testTotalHours = 40;
        final Content content = new Course(testTitle, testDescription, testTotalHours);

        assertEquals(
                testTitle,
                content.getTitle(),
                "Title should be the same as the one passed on constructor"
        );

        assertEquals(
                testDescription,
                content.getDescription(),
                "Description should be the same as the one passed on constructor"
        );

        assertEquals(
                testTotalHours * DEFAULT_XP,
                content.xpAwarded(),
                "xpAwarded expected to be the product of 'total hours' with 'default xp'"
        );
    }


    @Test
    void testMentorship() {
        final String testTitle = "JavaOO";
        final String testDescription = "exercise on object orientation with java";
        final Content content = new Mentorship(testTitle, testDescription, LocalDateTime.now());

        assertEquals(
                testTitle,
                content.getTitle(),
                "Title should be the same as the one passed on constructor"
        );

        assertEquals(
                testDescription,
                content.getDescription(),
                "Description should be the same as the one passed on constructor"
        );

        assertEquals(
                20 + DEFAULT_XP,
                content.xpAwarded(),
                "xpAwarded expected to be the sum of 'default xp' with 20"
        );
    }

    @Test
    void testDev() {
        final Course course = new Course("JavaOO", "Object Orient java", 20);

        final Mentorship mentorship = new Mentorship(
                "Types vs Classes",
                "A domain modeling comparative approach between Haskell and Java",
                LocalDateTime.now()
        );

        final Dev dev = new Dev("Mr. Yes");

        double devInitialXp = dev.getXp();
        assertEquals(0.0, devInitialXp, "dev initial xp should be 0.0");

        dev.subscribeContent(course);
        dev.subscribeContent(mentorship);

        final Content actualFirstContent = dev.nextContent().orElse(null);
        final String messageNextContent = "nextContent should retrieve subscriptions in order of subscription";
        assertEquals(course, actualFirstContent, messageNextContent);

        dev.completeContent(actualFirstContent);
        double devXpAfterCourse = dev.getXp();
        double expectedXpAfterCourse = course.xpAwarded();
        assertEquals(expectedXpAfterCourse, devXpAfterCourse,
    "after just course complete dev xp should match the completed course"
        );

        final Content actualSecondContent = dev.nextContent().orElse(null);
        final String messageSecondContent =
                "After completing the first content nextContent should retrieve the second subscribed content";
        assertEquals(mentorship, actualSecondContent, messageSecondContent);

        dev.completeContent(actualSecondContent);
        double devXpAfterCourseAndMentorship = dev.getXp();
        double expectedXpAfterCourseAndMentorship = course.xpAwarded() + mentorship.xpAwarded();
        assertEquals(expectedXpAfterCourseAndMentorship, devXpAfterCourseAndMentorship,
                "after just course and mentorship complete dev xp should match the sum of both course and mentorship xp"
        );

        dev.subscribeContent(course);

        final Content shouldBeNull = dev.nextContent().orElse(null);
        final String messageShouldBeNull =
                "When the list of subscriptions is empty and an already complete content is subscribed then this content" +
                        "should not be added to the subscription and nextContent should return empty";
        assertNull(shouldBeNull, messageShouldBeNull);

    }

    @Test
    void testBootcamp() {
        final Course course = new Course("JavaOO", "Object Orient java", 20);

        final Mentorship mentorship = new Mentorship(
                "Types vs Classes",
                "A domain modeling comparative approach between Haskell and Java",
                LocalDateTime.now()
        );

        final LinkedHashSet<Content> contents = Stream.of(course, mentorship)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        final Dev dev = new Dev("Mr. Yes");

        final Bootcamp bootcamp = new Bootcamp(
                "Bootcamp Domain Driven Java",
                "Dominating the complexity of your domain",
                new HashSet<>(),
                contents
        );

        final String expectedSubscribedEmpty = "";
        final String actualSubscribedEmpty = bootcamp.subscribedDevs();
        assertEquals(expectedSubscribedEmpty, actualSubscribedEmpty);

        bootcamp.subscribe(dev);

        final String expectedSubscribedDevs = dev.toString();
        final String actualSubscribedDevs = bootcamp.subscribedDevs();
        assertEquals(expectedSubscribedDevs, actualSubscribedDevs);

        final double actualXpAwarded = bootcamp.xpAwarded();
        final double expectedXpAwarded = contents.size() * 10 * DEFAULT_XP;
        assertEquals(expectedXpAwarded, actualXpAwarded);

        dev.nextContent().ifPresent(dev::completeContent);
        final double expectedDevXpAfterCourse = course.xpAwarded();
        final double actualDevXpAfterCourse = dev.getXp();
        assertEquals(expectedDevXpAfterCourse, actualDevXpAfterCourse);

        dev.nextContent().ifPresent(dev::completeContent);
        final double expectedDevXpAfterMentorship = course.xpAwarded() + mentorship.xpAwarded();
        final double actualDevXpAfterMentorship = dev.getXp();
        assertEquals(expectedDevXpAfterMentorship, actualDevXpAfterMentorship);

        dev.nextContent().ifPresent(dev::completeContent);
        final double expectedDevXpAfterBootcampComplete = course.xpAwarded() + mentorship.xpAwarded() + bootcamp.xpAwarded();
        final double actualDevXpAfterBootcampComplete = dev.getXp();
        assertEquals(expectedDevXpAfterBootcampComplete, actualDevXpAfterBootcampComplete);

        final Content contentEmpty = dev.nextContent().orElse(null);
        assertNull(contentEmpty);
    }
}
