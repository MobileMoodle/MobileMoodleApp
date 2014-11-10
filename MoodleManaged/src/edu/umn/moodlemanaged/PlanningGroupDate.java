package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class PlanningGroupDate {
    public String string; // Holds date
    public final List<Event> children = new ArrayList<Event>(); // Hold events

    public PlanningGroupDate(String string) {
        this.string = string;
    }
}
