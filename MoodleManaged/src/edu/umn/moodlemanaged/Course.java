package edu.umn.moodlemanaged;

public class Course {
	private String id;
	private String name;
	
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
