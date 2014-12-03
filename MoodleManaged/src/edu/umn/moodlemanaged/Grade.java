package edu.umn.moodlemanaged;

import android.database.Cursor;

import java.util.Date;

public class Grade {
    public String name;
    public boolean fixed; // true if set by prof, false if set by student
    public boolean setStudent;
    public int id;
    public int eid;
    public int percentage;
    public double score;
    public double total;

    public Grade(String name,int percentage, double score,double total, boolean b, boolean setStudent) {
        this.name = name;
        this.fixed = b;
        this.setStudent = setStudent;
        this.percentage= percentage;
        this.score = score;
        this.total = total;
    }
    public Grade(int id, int eid, String name, boolean fixed, int percentage, double score, double total){
        this.id = id ;
        this.eid = eid;
        this.name = name;
        this.fixed = fixed;
        this.percentage = percentage;
        this.score = score;
        this.total = total;
    }
    public Grade(Cursor cursor){
        this.name=cursor.getString(cursor.getColumnIndex("name"));
        this.fixed = new Boolean(cursor.getString(cursor.getColumnIndex("fixed")));
        this.percentage = new Integer(cursor.getString(cursor.getColumnIndex("percentage")));
        //this.id = new Integer(cursor.getString(cursor.getColumnIndex("id")));
        //this.eid = new Integer(cursor.getString(cursor.getColumnIndex("eid")));
        this.score = new Double(cursor.getString(cursor.getColumnIndex("score")));
        this.total = new Double(cursor.getString(cursor.getColumnIndex("total")));
    }

    public void setCoursework(String s) {this.name = s; }

    public void setFinal(boolean b){ this.fixed = b; }
    public void setSetStudent(boolean b){ this.setStudent = b; }

    //public void setPercent(String s){ this.percent = s; }
    public void setPercentage(int p){ this.percentage = p ;}
    public int getPercentage(){ return percentage; }

    public boolean isFinal(){ return fixed; }
    public boolean isSetStudent(){return setStudent; }
    public double getScore(){ return 100*(this.score/this.total);}
    public void setScore(double s){this.score = s/100*this.total;}
    public String getCoursework(){ return name; }

}
