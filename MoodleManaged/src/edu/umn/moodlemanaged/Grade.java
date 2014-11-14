package edu.umn.moodlemanaged;

public class Grade {
    private String coursework;
    private boolean isFinal; // true if set by prof, false if set by student
    private int percentage;
    private double score;
    private double total;
    public Grade(String cw,int percentage, double score,double total, boolean b) {
        this.coursework = cw;
        this.isFinal = b;
        this.percentage= percentage;
        this.score = score;
        this.total = total;
    }

    public void setCoursework(String s) {this.coursework = s; }

    public void setFinal(boolean b){ this.isFinal = b; }

    //public void setPercent(String s){ this.percent = s; }
    public void setPercentage(int p){ this.percentage = p ;}
    public int getPercentage(){ return percentage; }

    public boolean isFinal(){ return isFinal; }
    public double getScore(){ return 100*(this.score/this.total);}
    public void setScore(double s){this.score = s/100*this.total;}
    public String getCoursework(){ return coursework; }

}
