package model;

import java.util.LinkedHashSet;
import model.content.Content;
import java.util.Optional;

public class Dev {
    private final String name;
    private final LinkedHashSet<Content> subscribedContent;
    private final LinkedHashSet<Content> completedContent;

    public Dev(String name) {
        this.name = name;
        subscribedContent = new LinkedHashSet<>();
        completedContent = new LinkedHashSet<>();
    }

    public Dev(String updateName, Dev contentToCopy) {
        this.name = updateName;
        subscribedContent = contentToCopy.subscribedContent;
        completedContent = contentToCopy.completedContent;
    }

    public double getXp() {
        return completedContent.stream().mapToDouble(Content::xpAwarded).sum();
    }

    public void subscribeContent(Content content) {
        if(!completedContent.contains(content)) {
            subscribedContent.add(content);
        }
    }

    public void unsubscribeContent(Content content) {
        subscribedContent.remove(content);
    }

    public Optional<Content> nextContent() {
        return subscribedContent.stream().findFirst();
    }

    public boolean completeContent(Content content) {
        if(subscribedContent.contains(content)) {
            subscribedContent.remove(content);
            completedContent.add(content);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(Dev :Name " + name + " :Xp " + getXp() + ')';
    }
}
