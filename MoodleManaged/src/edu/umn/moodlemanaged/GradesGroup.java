package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class GradesGroup {
	public String string; // E.g. Exams (60%)
	public final List<Grade> children = new ArrayList<Grade>();

	public GradesGroup(String string) {
		this.string = string;
	}
}
