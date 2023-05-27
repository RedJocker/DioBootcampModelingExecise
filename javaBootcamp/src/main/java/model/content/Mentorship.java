package model.content;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Mentorship extends Content {

    final private LocalDateTime date;

    public Mentorship(String title, String description, LocalDateTime date) {
        super(title, description);
        this.date = date;
    }

    @Override
    public double xpAwarded() {
        return DEFAULT_XP + 20.0;
    }

    @Override
    public String toString() {
        final int descLength = getDescription().length();
        final int lineBreak = 75;
        final String fmt =
                "================================================================================\n" +
                "| Mentorship: %-65s|\n" +
                "| Date: %-71s|\n" +
                "| Awarded XP: %-65.2f|\n" +
                "================================================================================\n" +
                Stream.iterate(0, i -> i < descLength, i -> i + lineBreak)
                        .map(i -> getDescription().substring(i, Math.min(i + lineBreak + 1, descLength)))
                        .map(str -> wrappedDescriptionLine(str, lineBreak))
                        .collect(Collectors.joining()) +
                "================================================================================\n";

        return String.format(fmt, getTitle(), date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), xpAwarded());
    }
}
