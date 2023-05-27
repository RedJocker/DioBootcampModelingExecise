package model.content;

public abstract class Content {
    public static double DEFAULT_XP = 10.00;
    
    private final String title;
    private final String description;

    public Content(String title, String description) {  
        this.title = title;                             
        this.description = description;                 
    }

    public abstract double xpAwarded();

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    protected String wrappedDescriptionLine(String descriptionLineWithFirstCharOfNext, int lineBreak) {
        final String d = descriptionLineWithFirstCharOfNext;

        final int lastIndex = Math.min(d.length(), lineBreak);
        final boolean shouldPutWrapSign = lastIndex == lineBreak
                && Character.isLetter(d.charAt(lineBreak - 1))
                && Character.isLetter(d.charAt(lineBreak));

        return String.format("| %-75s%s |\n", d.substring(0, lastIndex).trim(),  shouldPutWrapSign ? "-" : " ");
    }
}
