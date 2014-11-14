package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class PlanningGroupCourse {
    public String string; // Holds course
    public final List<Event> children = new ArrayList<Event>(); // Hold events

    public PlanningGroupCourse(String string) {
        this.string = string;
    }
}
