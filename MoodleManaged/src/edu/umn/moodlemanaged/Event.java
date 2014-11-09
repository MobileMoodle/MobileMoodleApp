package edu.umn.moodlemanaged;

public class Event {
    public String text; // E.g. CSCI 5115: Development Complete (1:30PM)*
    public boolean isChecked; // Stores whether the event has been checked off

    public Event(String t, boolean b) {
        this.text = t;
        this.isChecked = b;
    }
}
