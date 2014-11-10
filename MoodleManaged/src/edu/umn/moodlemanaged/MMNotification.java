package edu.umn.moodlemanaged;

public class MMNotification {
    public String not; // E.g. Exams (60%)
    // TODO make private, give a date/time, boolean for is dismissed, etc.

    public MMNotification(String notification) {
        this.not = notification;
    }
}
