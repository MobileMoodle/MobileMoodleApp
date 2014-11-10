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

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

public class GradesFragment extends Fragment {
	private SparseArray<GradesGroup> groups = new SparseArray<GradesGroup>();
    private String desiredGrade = "";
    private View view;
    ArrayAdapter<String> coursesAdapter;

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
                        break;
                    case 1: desiredGrade = "F";
                        break;
                    case 2: desiredGrade = "D-";
                        break;
                    case 3: desiredGrade = "D";
                        break;
                    case 4: desiredGrade = "D+";
                        break;
                    case 5: desiredGrade = "C-";
                        break;
                    case 6: desiredGrade = "C";
                        break;
                    case 7: desiredGrade = "C+";
                        break;
                    case 8: desiredGrade = "B-";
                        break;
                    case 9: desiredGrade = "B";
                        break;
                    case 10: desiredGrade = "B+";
                        break;
                    case 11: desiredGrade = "A-";
                        break;
                    case 12: desiredGrade = "A";
                        break;
                    default: desiredGrade = "";
                        break;
                }

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
        GradesGroup gradesGroup = new GradesGroup("Exams (40%)");
        gradesGroup.children.add(new Grade("Midterm (15%)", "88%", true));
        gradesGroup.children.add(new Grade("Final (25%)", "", false));
        groups.append(0, gradesGroup);
        gradesGroup = new GradesGroup("Assignments (60%)");
        gradesGroup.children.add(new Grade("Assignment 1 (5%)", "100%", true));
        gradesGroup.children.add(new Grade("Assignment 2 (5%)", "100%", true));
        gradesGroup.children.add(new Grade("Assignment 3 (5%)", "97%", true));
        gradesGroup.children.add(new Grade("Assignment 4 (5%)", "100%", true));
        gradesGroup.children.add(new Grade("Assignment 5 (5%)", "100%", true));
        gradesGroup.children.add(new Grade("Assignment 6 (5%)", "100%", true));
        gradesGroup.children.add(new Grade("Assignment 7 (5%)", "", false));
        gradesGroup.children.add(new Grade("Assignment 8 (5%)", "", false));
        groups.append(1, gradesGroup);
	}

}
