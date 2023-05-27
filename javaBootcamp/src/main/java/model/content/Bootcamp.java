package model.content;

import model.Dev;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Bootcamp extends Content {

    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final Set<Dev> devs;
    private final LinkedHashSet<Content> contents;

    public Bootcamp(String title, String description) {
        super(title, description);
        this.initialDate = LocalDate.now();
        this.finalDate = initialDate.plusDays(45);
        this.devs = new HashSet<>();
        this.contents = new LinkedHashSet<>();
    }

    public Bootcamp(String title, String description, Set<Dev> devs, LinkedHashSet<Content> contents) {
        super(title, description);
        this.initialDate = LocalDate.now();
        this.finalDate = initialDate.plusDays(45);
        this.devs = devs;
        this.contents = contents;
        this.devs.forEach(dev -> this.contents.forEach(dev::subscribeContent));
    }

    public void subscribe(Dev dev) {
        devs.add(dev);
        this.contents.forEach(dev::subscribeContent);
        dev.subscribeContent(this);
    }

    public void unsubscribe(Dev dev) {
        devs.remove(dev);
        this.contents.forEach(dev::unsubscribeContent);
    }

    public String subscribedDevs() {
        return devs.stream().map(Dev::toString).collect(Collectors.joining("\n"));
    } 
    
    @Override
    public double xpAwarded() {
        return this.contents.size() * 10 * DEFAULT_XP;
    }

    @Override
    public String toString() {
        return "\n-------------++++++++++++++  B  O  O  T  C  A  M  P  ++++++++++++++-------------" +
                "\n:Title: " + getTitle() +
                "\n:Description: " + getDescription() +
                "\n:Initial Date: " + initialDate +
                "\n:Final Date: " + finalDate +
                "\n:XP Awarded: " + xpAwarded() +
                "\n**** CONTENT ****\n" +
                contents.stream().map(Content::toString).collect(Collectors.joining("\n")) +
                "-------------++++++++++++++  B  O  O  T  C  A  M  P  ++++++++++++++-------------\n"
                ;
    }
}
