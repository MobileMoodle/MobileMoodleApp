package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class Group {
	public String string; // E.g. Exams (60%)
	public final List<String> children = new ArrayList<String>(); // E.g. Assignment 1 (5%)

	public Group(String string) {
		this.string = string;
	}
}
