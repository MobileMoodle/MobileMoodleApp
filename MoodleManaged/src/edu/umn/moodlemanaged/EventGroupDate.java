package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class EventGroupDate {
    public String string; // Holds date
    public final List<Event> children = new ArrayList<Event>(); // Hold events

    public EventGroupDate(String string) {
        this.string = string;
    }
}
