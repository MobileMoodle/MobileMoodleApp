package edu.umn.moodlemanaged;

import android.database.Cursor;

public class Course {
	public String number; // E.g. CSCI 5115
	public String name; // E.g. User Interface Design, Implementation & Evaluation
    public int numNotifications;
	public String syllabus;
    public int id;

    public Course(String number, String name){
        this.number = number;
        this.name = name;
    }

    public Course(String number, String name, int numNotifications){
        this.number = number;
        this.name = name;
        this.numNotifications = numNotifications;
    }
    public Course(int id , String number , String name, String syllabus){
        this.id = id;
        this.number = number;
        this.name = name;
        this.syllabus = syllabus;
    }
    public Course(Cursor cursor){
        this.id = new Integer(cursor.getString(cursor.getColumnIndex("id")));
        this.name = cursor.getString(cursor.getColumnIndex("name"));
        this.syllabus = cursor.getString(cursor.getColumnIndex("syllabus"));
        this.number = cursor.getString(cursor.getColumnIndex("number"));
    }
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return number;
	}

    public int getNumNotifications() {
        return numNotifications;
    }

    public void setNumNotifications(int n) { this.numNotifications = n; }
}
