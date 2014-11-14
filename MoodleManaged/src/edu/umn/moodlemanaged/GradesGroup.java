package edu.umn.moodlemanaged;

import java.util.ArrayList;
import java.util.List;

public class GradesGroup {
	public String name; // E.g. Exams (60%)
	public final List<Grade> children = new ArrayList<Grade>();
    private int percentage =0;
	public GradesGroup(String string) {
		this.name= string;
        this.percentage =0 ;
	}
    public void addChildren(Grade g){
        this.children.add(g);
        this.percentage += g.getPercentage();
    }

    public int getPercentage(){
        return percentage;
    }
}
