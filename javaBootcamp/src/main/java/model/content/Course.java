package model.content;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Course extends Content  {

    private final int totalHours;

    public Course(String title, String description, int totalHours) {
        super(title, description);
        this.totalHours = totalHours;
    }

    @Override
    public double xpAwarded() {
        return DEFAULT_XP *  totalHours;
    }

    @Override
    public String toString() {
        final int descLength = getDescription().length();
        char a = 'a';
        final int lineBreak = 75;
        final String fmt =
                "================================================================================\n" +
                        "| Course: %-69s|\n" +
                        "| Total Hours: %-64s|\n" +
                        "| Awarded XP: %-65.2f|\n" +
                        "================================================================================\n" +
                        Stream.iterate(0, i -> i < descLength, i -> i + lineBreak)
                                .map(i -> getDescription().substring(i, Math.min(i + lineBreak + 1, descLength)))
                                .map(str -> wrappedDescriptionLine(str, lineBreak))
                                .collect(Collectors.joining()) +
                        "================================================================================\n"
                ;

        return String.format(fmt, getTitle(), totalHours, xpAwarded());
    }

    private String wrappedDescriptionLine(String descriptionLineWithFirstCharOfNext, int lineBreak) {
        final String d = descriptionLineWithFirstCharOfNext;

        final int lastIndex = Math.min(d.length(), lineBreak);
        final boolean shouldPutWrapSign = lastIndex == lineBreak
                && Character.isLetter(d.charAt(lineBreak - 1))
                && Character.isLetter(d.charAt(lineBreak));

        return String.format("| %-75s%s |\n", d.substring(0, lastIndex).trim(),  shouldPutWrapSign ? "-" : " ");
    }
}
