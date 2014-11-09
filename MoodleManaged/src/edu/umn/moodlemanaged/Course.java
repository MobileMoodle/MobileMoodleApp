package edu.umn.moodlemanaged;

public class Course {
	private String id; // E.g. CSCI 5115
	private String name; // E.g. User Interface Design, Implementation & Evaluation
    private int numNotifications;
	
	public Course(String id, String name){
		this.id = id;
		this.name = name;
	}

    public Course(String id, String name, int numNotifications){
        this.id = id;
        this.name = name;
        this.numNotifications = numNotifications;
    }

	public String getName() {
		return name;
	}
	
	public String getID() {
		return id;
	}

    public int getNumNotifications() {
        return numNotifications;
    }

    public void setNumNotifications(int n) { this.numNotifications = n; }
}
