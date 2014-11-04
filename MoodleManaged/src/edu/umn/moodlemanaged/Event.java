package edu.umn.moodlemanaged;

public class Event {
    private String title;
    private String id;
    private String type;
    private String weight;
    private String dueDate;
    private String dueTime;
    private String description;
    private String details;

    public Event(String title, String id, String type, String weight,
                 String dueDate, String dueTime, String description, String details){
        this.title = title;
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.description = description;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getID() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getWeight() { return weight; }

    public String getDueDate() {
        return dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }
}