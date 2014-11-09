package edu.umn.moodlemanaged;

public class Course {
	private String id; // E.g. CSCI 5115
	private String name; // E.g. User Interface Design, Implementation & Evaluation
	
	public Course(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getID() {
		return id;
	}
}
