package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

public class GradesFragment extends Fragment {
	private SparseArray<GradesGroup> groups = new SparseArray<GradesGroup>();
    private String desiredGrade = "";
    private View view;
    ArrayAdapter<String> coursesAdapter;
    private int totalWanted = 0;
    private int currentAvg = 0;
    private int aCutOff;
    private int amCutOff;
    private int bpCutOff;
    private int bCutOff;
    private int bmCutOff;
    private int cpCutOff;
    private int cCutOff;
    private int cmCutOff;
    private int dpCutOff;
    private int dCutOff;
    private int dmCutOff;
    private int fCutOff;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.grades_tab, container, false);

        // Populate grades data
		createData();

        Spinner courses = (Spinner) view.findViewById(R.id.course_spinner_g);
        coursesAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");
        courses.setAdapter(coursesAdapter);

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
                        totalWanted = 0;
                        resetNonFinal();
                        break;
                    case 1: desiredGrade = "F";
                        totalWanted = fCutOff;
                        updateMinNeeded();
                        break;
                    case 2: desiredGrade = "D-";
                        totalWanted = dmCutOff;
                        updateMinNeeded();
                        break;
                    case 3: desiredGrade = "D";
                        totalWanted = dCutOff;
                        updateMinNeeded();
                        break;
                    case 4: desiredGrade = "D+";
                        totalWanted = dpCutOff;
                        updateMinNeeded();
                        break;
                    case 5: desiredGrade = "C-";
                        totalWanted = cmCutOff;
                        updateMinNeeded();
                        break;
                    case 6: desiredGrade = "C";
                        totalWanted = cCutOff;
                        updateMinNeeded();
                        break;
                    case 7: desiredGrade = "C+";
                        totalWanted = cpCutOff;
                        updateMinNeeded();
                        break;
                    case 8: desiredGrade = "B-";
                        totalWanted = bmCutOff;
                        updateMinNeeded();
                        break;
                    case 9: desiredGrade = "B";
                        totalWanted = bCutOff;
                        updateMinNeeded();
                        break;
                    case 10: desiredGrade = "B+";
                        totalWanted = bpCutOff;
                        updateMinNeeded();
                        break;
                    case 11: desiredGrade = "A-";
                        totalWanted = amCutOff;
                        updateMinNeeded();
                        break;
                    case 12: desiredGrade = "A";
                        totalWanted = aCutOff;
                        updateMinNeeded();
                        break;
                    default: desiredGrade = "";
                        resetNonFinal();
                        break;
                }

                TextView tv = (TextView) view.findViewById(R.id.tv_grade_i_want);
                tv.setText("Grade I want: " + desiredGrade);

                // TODO Change percents
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

		ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
		GradesCustomAdapter adapter = new GradesCustomAdapter(getActivity(), groups);
		listView.setAdapter(adapter);
		
		return view;
	}

    // TODO Integrate with cloud
    /**
     * Add Grades (Mock-up ONLY)
     * TODO Add tabs for other courses
     */
	public void createData() {
        setCutOffs(93, 90, 85, 85, 82, 75, 75, 73, 65, 65, 63, 55);
        GradesGroup gradesGroup = new GradesGroup("Exams (40%)");
        gradesGroup.children.add(new Grade("Midterm", 15, 88, true, false));
        gradesGroup.children.add(new Grade("Final", 25, -1, false, false));
        groups.append(0, gradesGroup);
        gradesGroup = new GradesGroup("Assignments (60%)");
        gradesGroup.children.add(new Grade("Assignment 1", 7.5, 100, true, false));
        gradesGroup.children.add(new Grade("Assignment 2", 7.5, 100, true, false));
        gradesGroup.children.add(new Grade("Assignment 3", 7.5, 97, true, false));
        gradesGroup.children.add(new Grade("Assignment 4", 7.5, 100, true, false));
        gradesGroup.children.add(new Grade("Assignment 5", 7.5, 100, true, false));
        gradesGroup.children.add(new Grade("Assignment 6", 7.5, 100, true, false));
        gradesGroup.children.add(new Grade("Assignment 7", 7.5, -1, false, false));
        gradesGroup.children.add(new Grade("Assignment 8", 7.5, -1, false, false));
        groups.append(1, gradesGroup);
	}

    public void resetNonFinal()
    {
        for(int i = 0; i < groups.size(); i++)
        {
            int key = groups.keyAt(i);
            GradesGroup obj = groups.get(key);
            for(Grade current : obj.children)
            {
                if(!current.isFinal())
                {
                    current.setPercent(-1);
                }
            }
        }
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
        listView.invalidateViews();
    }

    public void updateMinNeeded()
    {
        int currentMinAvg = 0;
        for(int i = 0; i < groups.size(); i++)
        {
            int key = groups.keyAt(i);
            GradesGroup obj = groups.get(key);
            for(Grade current : obj.children)
            {
                if(!current.isFinal() && !current.wasSet())
                {
                }
            }
        }
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
        listView.invalidateViews();
    }

    public void setCutOffs(int a, int am, int bp, int b, int bm, int cp, int c, int cm, int dp, int d, int dm, int f)
    {
        aCutOff = a;
        amCutOff = am;
        bpCutOff = bp;
        bCutOff = b;
        bmCutOff = bm;
        cpCutOff = cp;
        cCutOff = c;
        cmCutOff = cm;
        dpCutOff = dp;
        dCutOff = d;
        dmCutOff = dm;
        fCutOff = f;
    }

}
