package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.mtp.MtpObjectInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

public class GradesFragment extends Fragment {
	private static SparseArray<GradesGroup> groups = new SparseArray<GradesGroup>();
    private static ArrayList<SparseArray<GradesGroup>> grouplist = new ArrayList<SparseArray<GradesGroup>>();
    private String desiredGrade = "";
    private View view;
    public static Context thiscontext;
    public static double currentWanted = -1;
    public static GradesCustomAdapter adapter ;



    ArrayAdapter<String> courseNameAdapter;
    ArrayList<Course> courselist;
    public void getCurrentGrade()
    {
        int numPossible = 0;
        int totalGained = 0;
        for (int i =0;i<groups.size();i++){
            GradesGroup group =  groups.get(groups.keyAt(i));
            for (Grade g : group.children)
            {
                Log.e("debug", "Current course is: " + g.name + ". Is final is: " + g.isFinal() + ". Has value? " + g.score);
                if(g.isFinal())
                {
                    numPossible += g.total;
                    totalGained += g.score;
                }
            }
        }
        if(numPossible > 0)
        {
            TextView courses = (TextView) view.findViewById(R.id.grade_i_have);
            courses.setText("Grade I have: " + (totalGained/numPossible) + "%");
            Log.i("debug", "TOTAL GRADE IS: " + (totalGained/numPossible));
        }
    }
    public void clearFakeGrades(){
        for (int i =0;i<groups.size();i++){
            GradesGroup group =  groups.get(groups.keyAt(i));
            for (Grade g : group.children){
                if(!g.isFinal())
                {
                    if(g.isSetStudent())
                    {
                        g.setSetStudent(false);
                    }
                    g.setScore(-1);
                }
            }
        }
    }
    public static boolean assignFakeGrades(double target){
        double used_percentage =0 ;
        double total_percentage = 0;

        double sum =0 ;
        for(int i=0 ;i < groups.size();i++){
            int key = groups.keyAt(i);
            GradesGroup group =  groups.get(key);
            total_percentage += group.getPercentage();
            for( Grade g : group.children){
                if(g.isFinal() || g.isSetStudent()){
                    double weighted_scores =g.getPercentage()*g.getScore()/100;
                    sum+= weighted_scores;
                    used_percentage+= g.getPercentage();
                }
            }
        }
        double unused_percentage = total_percentage-used_percentage;
        if((unused_percentage+sum)/total_percentage<(target/100)){
            return false;
        }
        double required_sum =(target/100)*total_percentage-sum;
        double required_sum_per_percent = required_sum / unused_percentage;
        for (int i =0;i<groups.size();i++){
            GradesGroup group =  groups.get(groups.keyAt(i));
            for (Grade g : group.children){
                if(!g.isFinal() && !g.isSetStudent()){
                    g.setScore(g.getPercentage()*required_sum_per_percent);
                }
            }
        }
        Log.i("IN assign fake grades",""+required_sum);
        //view.invalidate();

        adapter.notifyDataSetChanged();
        return true;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thiscontext = container.getContext();
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.grades_tab, container, false);

        // Populate grades data
		//createData();

        //Set the grade I Have

        Spinner courses = (Spinner) view.findViewById(R.id.course_spinner_g);
        courseNameAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        courseNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DBHelper mydb = MoodleManaged.mydb;
        courselist = mydb.getCourses();

        for (Course c : courselist){
            courseNameAdapter.add(c.getNumber());
            ////courselist.add(c);
        }
        grouplist = createData(courselist);
       if(courses.getSelectedItemPosition()==-1){
           courses.setSelection(0);
       }
        groups = grouplist.get(courses.getSelectedItemPosition());
        courses.setAdapter(courseNameAdapter);
        courses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course c;
                c = courselist.get(adapterView.getSelectedItemPosition());
                Log.i("on click ",c.name+" "+c.id);
                groups = grouplist.get(adapterView.getSelectedItemPosition());
                adapter.setGroup(groups);
                clearFakeGrades();
                SeekBar seekBar = (SeekBar) getActivity().findViewById(R.id.sb_grade_i_want);
                int itt = 0;
                seekBar.setProgress(itt);
                adapter.notifyDataSetChanged();
                getCurrentGrade();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Text for the desired grade
        TextView textView = (TextView) view.findViewById(R.id.tv_grade_i_want);
        textView.setText("Grade I want: " + desiredGrade);

        // Grades I want slider
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.sb_grade_i_want);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0: desiredGrade = "";
                        clearFakeGrades();
                        currentWanted = -1;
                        break;
                    case 1: desiredGrade = "F";
                        clearFakeGrades();
                        currentWanted = -1;
                        break;
                    case 2: desiredGrade = "D-";
                        assignFakeGrades(60);
                        currentWanted = 60;
                        break;
                    case 3: desiredGrade = "D";
                        assignFakeGrades(63);
                        currentWanted = 63;
                        break;
                    case 4: desiredGrade = "D+";
                        assignFakeGrades(67);
                        currentWanted = 67;
                        break;
                    case 5: desiredGrade = "C-";
                        assignFakeGrades(70);
                        currentWanted = 70;
                        break;
                    case 6: desiredGrade = "C";
                        assignFakeGrades(73);
                        currentWanted = 73;
                        break;
                    case 7: desiredGrade = "C+";
                        assignFakeGrades(77);
                        currentWanted = 77;
                        break;
                    case 8: desiredGrade = "B-";
                        assignFakeGrades(80);
                        currentWanted = 80;
                        break;
                    case 9: desiredGrade = "B";
                        assignFakeGrades(83);
                        currentWanted = 83;
                        break;
                    case 10: desiredGrade = "B+";
                        assignFakeGrades(87);
                        currentWanted = 87;
                        break;
                    case 11: desiredGrade = "A-";
                        assignFakeGrades(90);
                        currentWanted = 90;
                        break;
                    case 12: desiredGrade = "A";
                        assignFakeGrades(93);
                        currentWanted = 93;
                        break;
                    default: desiredGrade = "";
                        break;
                }
                ExpandableListView listView= (ExpandableListView) view.findViewById(R.id.grades_list);
                GradesCustomAdapter adapt = (GradesCustomAdapter) listView.getExpandableListAdapter();
                adapt.notifyDataSetChanged();

                TextView tv = (TextView) view.findViewById(R.id.tv_grade_i_want);
                tv.setText("Grade I want: " + desiredGrade);
                // TODO Change percents
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

		ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
		adapter = new GradesCustomAdapter(getActivity(), groups);
		listView.setAdapter(adapter);
        getCurrentGrade();
		
		return view;
	}

    // TODO Integrate with cloud
    /**
     * Add Grades (Mock-up ONLY)
     * TODO Add tabs for other courses
     */
    public SparseArray<GradesGroup> createData(int cid){
        int i =0;
        SparseArray<GradesGroup> ret = new SparseArray<GradesGroup>();
        GradesGroup gradesGroup = new GradesGroup("Exam");
        DBHelper db = MoodleManaged.mydb;
        ArrayList<Grade> bleh = db.getGrades();
        for(Grade g : bleh)
        {
            Log.e("debug", "Grade is: " + g.name + ". isFixed? = " + g.isFinal());
        }
        ArrayList<Grade> temp = db.getGrades(cid,"exam");
        for (Grade g : temp)
        {
            Log.e("debug", "Grade being added is: " + g.name + ". isFixed? = " + g.isFinal());
            gradesGroup.addChildren(g);
        }
        ret.append(i++, gradesGroup);
        gradesGroup = new GradesGroup("Assignment");

        temp = db.getGrades(cid,"assignment");
        for (Grade g : temp)
        {
            Log.e("debug", "Grade being added is: " + g.name + ". isFixed? = " + g.isFinal());
            gradesGroup.addChildren(g);
        }
        ret.append(i++,gradesGroup);
        gradesGroup = new GradesGroup("Quiz");
        temp = db.getGrades(cid,"quiz");
        for (Grade g : temp)
        {
            Log.e("debug", "Grade being added is: " + g.name + ". isFixed? = " + g.isFinal());
            gradesGroup.addChildren(g);
        }
        ret.append(i++,gradesGroup);
        gradesGroup = new GradesGroup("Project");
        temp = db.getGrades(cid,"project");
        for (Grade g : temp)
        {
            Log.e("debug", "Grade being added is: " + g.name + ". isFixed? = " + g.isFinal());
            gradesGroup.addChildren(g);
        }
        ret.append(i++,gradesGroup);
        return ret;
    }
	/*public void createData() {
        System.out.println("Creating Data");
        GradesGroup gradesGroup = new GradesGroup("Exams");

        gradesGroup.addChildren(new Grade("Midterm",15,88,100, true, false));
        gradesGroup.addChildren(new Grade("Final", 25,-1,100, false, false));

        groups.append(0, gradesGroup);
        gradesGroup = new GradesGroup("Assignments");
        gradesGroup.addChildren(new Grade("Assignment 1", 5, 100,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 2", 5, 100,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 3", 5, 97,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 4", 5, 100,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 5", 5, 100,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 6", 5, 100,100, true, false));
        gradesGroup.addChildren(new Grade("Assignment 7", 5, -1,100, false, false));
        gradesGroup.addChildren(new Grade("Assignment 8", 5, -1,100, false, false));

        groups.append(1, gradesGroup);
	}*/
    public ArrayList<SparseArray<GradesGroup>> createData(ArrayList<Course> clist){
        ArrayList<SparseArray<GradesGroup>> ret= new ArrayList<SparseArray<GradesGroup>>();
       for (Course c : clist){
           ret.add(createData(c.id));
       }
        return  ret;
    }

}
