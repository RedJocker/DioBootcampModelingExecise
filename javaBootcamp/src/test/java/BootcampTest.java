import model.content.Content;
import model.content.Course;
import model.content.Mentorship;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootcampTest {

    @Test
    void testContent() {
        final String testTitle = "JavaOO";
        final String testDescription = "exercise on object orientation with java";
        final Content content = new Content(testTitle, testDescription) {
            @Override
            public double xpAwarded() {
                return Content.DEFAULT_XP;
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
                testTotalHours * Content.DEFAULT_XP,
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
                20 + Content.DEFAULT_XP,
                content.xpAwarded(),
                "xpAwarded expected to be the sum of 'default xp' with 20"
        );
    }
}
