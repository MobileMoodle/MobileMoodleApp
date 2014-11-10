package edu.umn.moodlemanaged;

public class Grade {
    private String coursework;
    private String percent; // E.g. 97%
    private boolean isFinal; // true if set by prof, false if set by student

    public Grade(String cw, String p, boolean b) {
        this.coursework = cw;
        this.percent = p;
        this.isFinal = b;
    }

    public void setCoursework(String s) {this.coursework = s; }

    public void setIsFinal(boolean b){ this.isFinal = b; }

    public void setPercent(String s){ this.percent = s; }

    public String getPercent(){ return percent; }

    public boolean isFinal(){ return isFinal; }

    public String getCoursework(){ return coursework; }

}
