package model.content;

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
        return "Course{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", totalHours=" + totalHours +
                ", xpAwarded=" + xpAwarded() +
                '}';
    }
}
