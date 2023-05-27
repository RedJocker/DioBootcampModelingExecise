import model.Dev;
import model.content.Bootcamp;
import model.content.Content;
import model.content.Course;
import model.content.Mentorship;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Course course = new Course("JavaOO", "Object Orient java", 20);


        final Mentorship typesVsClasses = new Mentorship(
                "Types vs Classes",
                "A domain modeling comparative approach between Haskell and Java",
                LocalDateTime.now()
        );

        final LinkedHashSet<Content> contents = Stream.of(course, typesVsClasses)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        final Bootcamp bootcamp = new Bootcamp(
                "Bootcamp Domain Driven Java",
                "Dominating the complexity of your domain",
                new HashSet<Dev>(),
                contents
        );

        System.out.println(bootcamp);
    }
}
