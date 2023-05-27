import model.content.Content;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootcampTest {

    @Test
    void testContentTitle() {
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
    }

    @Test
    void testContentDescription() {
        final String testTitle = "JavaOO";
        final String testDescription = "exercise on object orintation with java";
        final Content content = new Content(testTitle, testDescription) {
            @Override
            public double xpAwarded() {
                return Content.DEFAULT_XP;
            }
        };

        assertEquals(
                testDescription,
                content.getDescription(),
                "Description should be the same as the one passed on constructor"
        );
    }
}
