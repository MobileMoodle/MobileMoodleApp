package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class NotificationsGroup {
    public String string; // E.g. Exams (60%)
    public final List<String> children = new ArrayList<String>();

    public NotificationsGroup(String string) {
        this.string = string;
    }
}
