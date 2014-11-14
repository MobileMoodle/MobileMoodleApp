package edu.umn.moodlemanaged;

public class Grade {
    private String coursework;
    private double weight; // E.g. 5
    private double percent; // E.g. 97
    private boolean isFinal; // true if set by prof, false if set by student
    private boolean wasSet; // True if user modified value, the other values should shift, not this one.

    public Grade(String cw, double w, double p, boolean b, boolean s) {
        this.coursework = cw;
        this.weight = w;
        this.percent = p;
        this.isFinal = b;
        this.wasSet = s;
    }

    public void setCoursework(String s) {this.coursework = s; }

    public void setIsFinal(boolean b){ this.isFinal = b; }

    public void setWasSet(boolean s){ this.wasSet = s; }

    public void setWeight(double w){ this.weight = w; }

    public void setPercent(double s){ this.percent = s; }

    public double getPercent(){ return percent; }

    public double getWeight(){ return weight; }

    public boolean isFinal(){ return isFinal; }

    public String getCoursework(){ return coursework; }

    public boolean wasSet(){ return wasSet; }



}
