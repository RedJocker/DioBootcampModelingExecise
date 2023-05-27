import model.content.Course;
import model.content.Mentorship;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Course("JavaOO", "Object Orient java", 20));

        System.out.println(
            new Mentorship(
            "Types vs Classes",
        "A domain modeling comparative approach between Haskell and Java",
                LocalDateTime.now()
            )
        );
        
    }
}
