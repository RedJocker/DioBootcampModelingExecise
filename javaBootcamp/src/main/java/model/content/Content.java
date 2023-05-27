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
}
